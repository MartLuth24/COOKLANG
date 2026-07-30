#  Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope Process (sempre da eseguire prima di lanciare lo script)

#.\.venv\Scripts\Activate.ps1 (per attivare l'ambiente virtuale, se necessario)

# Interrompi l'esecuzione in caso di errore
$ErrorActionPreference = "Stop"

# Imposta la directory di lavoro solo se lo script è eseguito da file .ps1
if ($PSScriptRoot) {
    Set-Location $PSScriptRoot
}

$PYTHON = ".venv\Scripts\python.exe"
$ANTLR = ".venv\Scripts\antlr4.exe"
$env:ANTLR4_TOOLS_ANTLR_VERSION = "4.13.2"

# 0. Virtual environment
Write-Host "==> 0. Controllo del virtual environment..." -ForegroundColor Cyan
if (-not (Test-Path $PYTHON)) {
    if (Test-Path ".venv") {
        Remove-Item -Path ".venv" -Recurse -Force
    }
    Write-Host "    Creazione di .venv e installazione delle dipendenze..." -ForegroundColor Yellow
    
    # 1. Cerca 'py' o 'python' nel PATH
    $sysPython = Get-Command py, python, python3 -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty Source

    # 2. Se non sono nel PATH, controlla le directory note (inclusa quella specifica del tuo PC)
    if (-not $sysPython) {
        $commonPaths = @(
            "$env:LOCALAPPDATA\Python\pythoncore-3.14-64\python.exe",
            "$env:LOCALAPPDATA\Programs\Python\Python*\python.exe",
            "$env:ProgramFiles\Python*\python.exe",
            "C:\Python*\python.exe"
        )
        $sysPython = Get-Item $commonPaths -ErrorAction SilentlyContinue | Select-Object -First 1 -ExpandProperty FullName
    }

    # 3. Se ancora non trova Python, interrompe con un errore
    if (-not $sysPython) {
        throw "ERRORE: Impossibile trovare 'python.exe' nel sistema."
    }

    Write-Host "    Uso l'eseguibile Python trovato in: $sysPython" -ForegroundColor Gray

    # Crea l'ambiente virtuale
    & $sysPython -m venv .venv
    & $PYTHON -m pip install --quiet --upgrade pip
    & $PYTHON -m pip install --quiet -r requirements.txt
}
Write-Host "    OK." -ForegroundColor Green

# 1. Pulizia
Write-Host "==> 1. Pulizia dei file generati e delle cache..." -ForegroundColor Cyan
Remove-Item -Path "source\CookLang*.py", "source\CookLang*.tokens", "source\CookLang*.interp" -ErrorAction SilentlyContinue
Get-ChildItem -Path . -Recurse -Directory -Filter "__pycache__" | Remove-Item -Recurse -Force -ErrorAction SilentlyContinue
Write-Host "    Pulizia completata." -ForegroundColor Green

# 2. Generazione del parser
Write-Host "==> 2. Generazione del parser ANTLR..." -ForegroundColor Cyan
& $ANTLR -Dlanguage=Python3 -visitor -no-listener CookLang.g4 -o source

# 3. File generati
Write-Host "==> 3. File generati in source/:" -ForegroundColor Cyan
Get-ChildItem -Path "source\CookLang*.py" -ErrorAction SilentlyContinue | ForEach-Object {
    Write-Host "      - $($_.Name)"
}

# 4. Esecuzione
Write-Host "==> 4. Esecuzione dei programmi d'esempio:" -ForegroundColor Cyan
Get-ChildItem -Path "programs\*.cook" -ErrorAction SilentlyContinue | ForEach-Object {
    Write-Host ""
    Write-Host "----- $($_.Name) -----" -ForegroundColor Yellow
    & $PYTHON source/main.py $_.FullName
}

Write-Host ""
Write-Host "==> Fatto!" -ForegroundColor Green