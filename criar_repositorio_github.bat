@echo off
echo ========================================
echo    CRIANDO REPOSITORIO NO GITHUB
echo ========================================
echo.

echo Verificando se o GitHub CLI esta instalado...
"C:\Program Files\GitHub CLI\gh.exe" --version
if %errorlevel% neq 0 (
    echo ERRO: GitHub CLI nao encontrado!
    echo Por favor, instale com: winget install GitHub.cli
    pause
    exit /b 1
)

echo.
echo Verificando se voce esta logado no GitHub...
"C:\Program Files\GitHub CLI\gh.exe" auth status
if %errorlevel% neq 0 (
    echo.
    echo Voce precisa fazer login no GitHub primeiro!
    echo Executando login...
    "C:\Program Files\GitHub CLI\gh.exe" auth login
    if %errorlevel% neq 0 (
        echo ERRO: Falha no login!
        pause
        exit /b 1
    )
)

echo.
echo Criando repositorio 'agenda-pessoal' no GitHub...
"C:\Program Files\GitHub CLI\gh.exe" repo create agenda-pessoal --public --description "Agenda pessoal com sistema de cores automatico - Versao Web e Android" --source=. --remote=origin --push

if %errorlevel% eq 0 (
    echo.
    echo ========================================
    echo     SUCESSO! REPOSITORIO CRIADO!
    echo ========================================
    echo.
    echo Seu repositorio foi criado em:
    "C:\Program Files\GitHub CLI\gh.exe" repo view --web
    echo.
    echo Para ativar GitHub Pages (hospedagem web gratuita):
    echo 1. Va para Settings no seu repositorio
    echo 2. Clique em Pages
    echo 3. Selecione 'Deploy from a branch'
    echo 4. Escolha 'main' branch
    echo 5. Clique Save
    echo.
    echo Sua agenda web estara disponivel em:
    echo https://SEU_USUARIO.github.io/agenda-pessoal
    echo.
) else (
    echo.
    echo ERRO: Falha ao criar repositorio!
    echo Verifique se:
    echo - Voce esta logado no GitHub
    echo - O nome 'agenda-pessoal' nao existe
    echo - Voce tem permissoes para criar repositorios
)

echo.
echo Pressione qualquer tecla para sair...
pause >nul