// Global variables to store workbook data
let workbooks = [];
let selectedSheets = new Set();

// DOM Elements
const fileInput = document.getElementById('fileInput');
const fileDropArea = document.getElementById('fileDropArea');
const fileList = document.getElementById('fileList');
const sheetSelectionContainer = document.getElementById('sheetSelectionContainer');
const mergeButton = document.getElementById('mergeButton');
const progressContainer = document.getElementById('progressContainer');
const progressBar = document.getElementById('progressBar');
const progressText = document.getElementById('progressText');
const outputFilename = document.getElementById('outputFilename');
const messageModal = document.getElementById('messageModal');
const modalTitle = document.getElementById('modalTitle');
const modalMessage = document.getElementById('modalMessage');
const modalOkButton = document.getElementById('modalOkButton');
const closeButton = document.querySelector('.close-button');

// Event Listeners
document.addEventListener('DOMContentLoaded', initApp);

function initApp() {
    // File selection events
    fileInput.addEventListener('change', handleFileSelection);
    fileDropArea.addEventListener('dragover', handleDragOver);
    fileDropArea.addEventListener('dragleave', handleDragLeave);
    fileDropArea.addEventListener('drop', handleFileDrop);
    fileDropArea.addEventListener('click', () => fileInput.click());

    // Merge button event
    mergeButton.addEventListener('click', handleMerge);

    // Modal events
    modalOkButton.addEventListener('click', closeModal);
    closeButton.addEventListener('click', closeModal);
    window.addEventListener('click', (e) => {
        if (e.target === messageModal) closeModal();
    });
}

// File Handling Functions
function handleDragOver(e) {
    e.preventDefault();
    fileDropArea.classList.add('active');
}

function handleDragLeave(e) {
    e.preventDefault();
    fileDropArea.classList.remove('active');
}

function handleFileDrop(e) {
    e.preventDefault();
    fileDropArea.classList.remove('active');
    
    const files = e.dataTransfer.files;
    processFiles(files);
}

function handleFileSelection(e) {
    const files = e.target.files;
    processFiles(files);
}

async function processFiles(files) {
    // Filter for Excel files
    const excelFiles = Array.from(files).filter(file => 
        file.name.endsWith('.xlsx') || 
        file.name.endsWith('.xls')
    );
    
    if (excelFiles.length === 0) {
        showMessage('Error', 'Please select valid Excel files (.xlsx or .xls)');
        return;
    }

    // Clear previous data if this is a new selection
    if (workbooks.length === 0) {
        clearFileList();
    }

    // Show loading state
    progressContainer.classList.remove('hidden');
    progressText.textContent = 'Reading files...';

    try {
        for (const file of excelFiles) {
            // Check if file is already added
            if (workbooks.some(wb => wb.name === file.name)) {
                continue;
            }

            // Read the file
            const workbook = await readExcelFile(file);
            workbooks.push({
                name: file.name,
                workbook: workbook,
                file: file
            });

            // Add to UI
            addFileToList(file.name);
        }

        // Update sheet selection UI
        updateSheetSelectionUI();
        
        // Enable merge button if sheets are selected
        updateMergeButtonState();
    } catch (error) {
        showMessage('Error', `Failed to read Excel files: ${error.message}`);
    } finally {
        progressContainer.classList.add('hidden');
    }
}

function readExcelFile(file) {
    return new Promise((resolve, reject) => {
        const reader = new FileReader();
        
        reader.onload = function(e) {
            try {
                const data = new Uint8Array(e.target.result);
                const workbook = new ExcelJS.Workbook();
                
                workbook.xlsx.load(data)
                    .then(wb => resolve(wb))
                    .catch(err => reject(err));
            } catch (error) {
                reject(error);
            }
        };
        
        reader.onerror = function() {
            reject(new Error('Failed to read file'));
        };
        
        reader.readAsArrayBuffer(file);
    });
}

function addFileToList(fileName) {
    const fileItem = document.createElement('div');
    fileItem.className = 'file-item';
    
    const fileNameSpan = document.createElement('span');
    fileNameSpan.className = 'file-name';
    fileNameSpan.textContent = fileName;
    
    const removeButton = document.createElement('span');
    removeButton.className = 'remove-file';
    removeButton.textContent = '×';
    removeButton.addEventListener('click', () => removeFile(fileName));
    
    fileItem.appendChild(fileNameSpan);
    fileItem.appendChild(removeButton);
    fileList.appendChild(fileItem);
}

function removeFile(fileName) {
    // Remove from workbooks array
    const index = workbooks.findIndex(wb => wb.name === fileName);
    if (index !== -1) {
        workbooks.splice(index, 1);
    }
    
    // Remove from UI
    const fileItems = fileList.querySelectorAll('.file-item');
    fileItems.forEach(item => {
        if (item.querySelector('.file-name').textContent === fileName) {
            item.remove();
        }
    });
    
    // Update sheet selection UI
    updateSheetSelectionUI();
    
    // Update merge button state
    updateMergeButtonState();
}

function clearFileList() {
    fileList.innerHTML = '';
    workbooks = [];
    selectedSheets.clear();
    updateSheetSelectionUI();
}

// Sheet Selection Functions
function updateSheetSelectionUI() {
    sheetSelectionContainer.innerHTML = '';
    
    if (workbooks.length === 0) {
        const instruction = document.createElement('p');
        instruction.className = 'instruction';
        instruction.textContent = 'Select files first to see available sheets';
        sheetSelectionContainer.appendChild(instruction);
        return;
    }
    
    // Create UI for each workbook and its sheets
    workbooks.forEach(wb => {
        const workbookItem = document.createElement('div');
        workbookItem.className = 'workbook-item';
        
        const workbookName = document.createElement('div');
        workbookName.className = 'workbook-name';
        workbookName.textContent = wb.name;
        
        const sheetList = document.createElement('div');
        sheetList.className = 'sheet-list';
        
        // Add sheets from this workbook
        wb.workbook.eachSheet(sheet => {
            const sheetId = `${wb.name}|${sheet.name}`;
            const sheetItem = document.createElement('div');
            sheetItem.className = 'sheet-item';
            
            const checkbox = document.createElement('input');
            checkbox.type = 'checkbox';
            checkbox.id = sheetId;
            checkbox.checked = selectedSheets.has(sheetId);
            checkbox.addEventListener('change', () => toggleSheetSelection(sheetId, checkbox.checked));
            
            const label = document.createElement('label');
            label.htmlFor = sheetId;
            label.textContent = sheet.name;
            
            sheetItem.appendChild(checkbox);
            sheetItem.appendChild(label);
            sheetList.appendChild(sheetItem);
        });
        
        workbookItem.appendChild(workbookName);
        workbookItem.appendChild(sheetList);
        sheetSelectionContainer.appendChild(workbookItem);
    });
}

function toggleSheetSelection(sheetId, isSelected) {
    if (isSelected) {
        selectedSheets.add(sheetId);
    } else {
        selectedSheets.delete(sheetId);
    }
    
    updateMergeButtonState();
}

function updateMergeButtonState() {
    mergeButton.disabled = selectedSheets.size === 0;
}

// Merge Functions
async function handleMerge() {
    if (selectedSheets.size === 0) {
        showMessage('Error', 'Please select at least one sheet to merge');
        return;
    }
    
    // Get merge options
    const mergeType = document.querySelector('input[name="mergeType"]:checked').value;
    const preserveFillColors = document.getElementById('preserveFillColors').checked;
    const preserveFontColors = document.getElementById('preserveFontColors').checked;
    const preserveBoldText = document.getElementById('preserveBoldText').checked;
    const insertHeaders = document.getElementById('insertHeaders').checked;
    const filename = document.getElementById('outputFilename').value || 'merged_workbook';
    
    // Show progress
    progressContainer.classList.remove('hidden');
    progressText.textContent = 'Merging sheets...';
    
    try {
        // Create a new workbook for the merged result
        const mergedWorkbook = new ExcelJS.Workbook();
        
        if (mergeType === 'singleSheet') {
            // Merge all selected sheets into a single worksheet
            await mergeSheetsIntoSingle(mergedWorkbook, {
                preserveFillColors,
                preserveFontColors,
                preserveBoldText,
                insertHeaders
            });
        } else {
            // Keep sheets separate in the merged workbook
            await mergeSheetsAsSeparate(mergedWorkbook, {
                preserveFillColors,
                preserveFontColors,
                preserveBoldText,
                insertHeaders
            });
        }
        
        // Save the merged workbook
        progressText.textContent = 'Generating file...';
        const buffer = await mergedWorkbook.xlsx.writeBuffer();
        saveAs(new Blob([buffer]), `${filename}.xlsx`);
        
        showMessage('Success', 'Sheets merged successfully! The file has been downloaded.');
    } catch (error) {
        showMessage('Error', `Failed to merge sheets: ${error.message}`);
    } finally {
        progressContainer.classList.add('hidden');
    }
}

async function mergeSheetsIntoSingle(mergedWorkbook, options) {
    // Create a single worksheet for all merged content
    const mergedSheet = mergedWorkbook.addWorksheet('Merged Data');
    let currentRow = 1;
    
    // Process each selected sheet
    for (const sheetId of selectedSheets) {
        const [workbookName, sheetName] = sheetId.split('|');
        const workbookData = workbooks.find(wb => wb.name === workbookName);
        
        if (!workbookData) continue;
        
        const sourceSheet = workbookData.workbook.getWorksheet(sheetName);
        
        // Add header row if option is enabled
        if (options.insertHeaders) {
            const headerCell = mergedSheet.getCell(currentRow, 1);
            headerCell.value = `${workbookName} - ${sheetName}`;
            headerCell.font = { bold: true, size: 14 };
            headerCell.fill = {
                type: 'pattern',
                pattern: 'solid',
                fgColor: { argb: 'FFE0E0E0' }
            };
            
            // Merge cells for header
            mergedSheet.mergeCells(currentRow, 1, currentRow, 10);
            currentRow++;
        }
        
        // Copy data from source sheet
        if (sourceSheet && sourceSheet.rowCount > 0) {
            // Get the used range
            const range = sourceSheet.dimensions;
            
            // Copy each row and cell
            for (let rowIndex = range.top; rowIndex <= range.bottom; rowIndex++) {
                const sourceRow = sourceSheet.getRow(rowIndex);
                const targetRow = mergedSheet.getRow(currentRow);
                
                // Copy each cell in the row
                for (let colIndex = range.left; colIndex <= range.right; colIndex++) {
                    const sourceCell = sourceRow.getCell(colIndex);
                    const targetCell = targetRow.getCell(colIndex);
                    
                    // Copy value
                    targetCell.value = sourceCell.value;
                    
                    // Copy formatting if enabled
                    copyFormatting(sourceCell, targetCell, options);
                }
                
                currentRow++;
            }
            
            // Add a blank row between sheets
            currentRow++;
        }
    }
    
    // Auto-size columns for better readability
    mergedSheet.columns.forEach(column => {
        let maxLength = 0;
        column.eachCell({ includeEmpty: false }, cell => {
            const length = cell.value ? cell.value.toString().length : 0;
            if (length > maxLength) {
                maxLength = length;
            }
        });
        column.width = Math.min(maxLength + 2, 50);
    });
}

async function mergeSheetsAsSeparate(mergedWorkbook, options) {
    // Process each selected sheet
    for (const sheetId of selectedSheets) {
        const [workbookName, sheetName] = sheetId.split('|');
        const workbookData = workbooks.find(wb => wb.name === workbookName);
        
        if (!workbookData) continue;
        
        const sourceSheet = workbookData.workbook.getWorksheet(sheetName);
        
        // Skip if sheet doesn't exist or is empty
        if (!sourceSheet || sourceSheet.rowCount === 0) continue;
        
        // Create a new sheet in the merged workbook
        // Use a unique name to avoid duplicates
        const targetSheetName = `${workbookName.split('.')[0]}_${sheetName}`.substring(0, 31);
        const targetSheet = mergedWorkbook.addWorksheet(targetSheetName);
        
        // Add header row if option is enabled
        if (options.insertHeaders) {
            const headerCell = targetSheet.getCell(1, 1);
            headerCell.value = `${workbookName} - ${sheetName}`;
            headerCell.font = { bold: true, size: 14 };
            headerCell.fill = {
                type: 'pattern',
                pattern: 'solid',
                fgColor: { argb: 'FFE0E0E0' }
            };
            
            // Merge cells for header
            targetSheet.mergeCells(1, 1, 1, 10);
        }
        
        const startRow = options.insertHeaders ? 2 : 1;
        
        // Get the used range
        const range = sourceSheet.dimensions;
        
        // Copy each row and cell
        for (let rowIndex = range.top; rowIndex <= range.bottom; rowIndex++) {
            const sourceRow = sourceSheet.getRow(rowIndex);
            const targetRow = targetSheet.getRow(rowIndex - range.top + startRow);
            
            // Copy each cell in the row
            for (let colIndex = range.left; colIndex <= range.right; colIndex++) {
                const sourceCell = sourceRow.getCell(colIndex);
                const targetCell = targetRow.getCell(colIndex);
                
                // Copy value
                targetCell.value = sourceCell.value;
                
                // Copy formatting if enabled
                copyFormatting(sourceCell, targetCell, options);
            }
        }
        
        // Auto-size columns for better readability
        targetSheet.columns.forEach(column => {
            let maxLength = 0;
            column.eachCell({ includeEmpty: false }, cell => {
                const length = cell.value ? cell.value.toString().length : 0;
                if (length > maxLength) {
                    maxLength = length;
                }
            });
            column.width = Math.min(maxLength + 2, 50);
        });
    }
}

function copyFormatting(sourceCell, targetCell, options) {
    // Copy fill color if enabled
    if (options.preserveFillColors && sourceCell.fill && sourceCell.fill.type) {
        targetCell.fill = JSON.parse(JSON.stringify(sourceCell.fill));
    }
    
    // Copy font properties if enabled
    if (sourceCell.font) {
        targetCell.font = targetCell.font || {};
        
        // Copy font color if enabled
        if (options.preserveFontColors && sourceCell.font.color) {
            targetCell.font.color = JSON.parse(JSON.stringify(sourceCell.font.color));
        }
        
        // Copy bold formatting if enabled
        if (options.preserveBoldText && sourceCell.font.bold) {
            targetCell.font.bold = sourceCell.font.bold;
        }
    }
    
    // Copy alignment
    if (sourceCell.alignment) {
        targetCell.alignment = JSON.parse(JSON.stringify(sourceCell.alignment));
    }
}

// Modal Functions
function showMessage(title, message) {
    modalTitle.textContent = title;
    modalMessage.textContent = message;
    messageModal.style.display = 'block';
}

function closeModal() {
    messageModal.style.display = 'none';
}

