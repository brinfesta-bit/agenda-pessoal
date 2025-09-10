# Script PowerShell para criar repositório no GitHub
Write-Host "========================================" -ForegroundColor Cyan
Write-Host "    CRIANDO REPOSITORIO NO GITHUB" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Verificar se GitHub CLI está instalado
Write-Host "Verificando se o GitHub CLI esta instalado..." -ForegroundColor Yellow
try {
    $ghVersion = & "C:\Program Files\GitHub CLI\gh.exe" --version
    Write-Host "✅ GitHub CLI encontrado: $($ghVersion.Split([Environment]::NewLine)[0])" -ForegroundColor Green
} catch {
    Write-Host "❌ ERRO: GitHub CLI nao encontrado!" -ForegroundColor Red
    Write-Host "Por favor, instale com: winget install GitHub.cli" -ForegroundColor Yellow
    Read-Host "Pressione Enter para sair"
    exit 1
}

Write-Host ""

# Verificar se está logado
Write-Host "Verificando se voce esta logado no GitHub..." -ForegroundColor Yellow
try {
    & "C:\Program Files\GitHub CLI\gh.exe" auth status | Out-Null
    Write-Host "✅ Voce esta logado no GitHub!" -ForegroundColor Green
} catch {
    Write-Host "⚠️  Voce precisa fazer login no GitHub primeiro!" -ForegroundColor Yellow
    Write-Host "Executando login..." -ForegroundColor Cyan
    
    try {
        & "C:\Program Files\GitHub CLI\gh.exe" auth login
        Write-Host "✅ Login realizado com sucesso!" -ForegroundColor Green
    } catch {
        Write-Host "❌ ERRO: Falha no login!" -ForegroundColor Red
        Read-Host "Pressione Enter para sair"
        exit 1
    }
}

Write-Host ""

# Criar repositório
Write-Host "Criando repositorio 'agenda-pessoal' no GitHub..." -ForegroundColor Yellow
Write-Host "Aguarde, isso pode levar alguns segundos..." -ForegroundColor Gray

try {
    $result = & "C:\Program Files\GitHub CLI\gh.exe" repo create agenda-pessoal --public --description "Agenda pessoal com sistema de cores automatico - Versao Web e Android" --source=. --remote=origin --push 2>&1
    
    if ($LASTEXITCODE -eq 0) {
        Write-Host ""
        Write-Host "========================================" -ForegroundColor Green
        Write-Host "     SUCESSO! REPOSITORIO CRIADO!" -ForegroundColor Green
        Write-Host "========================================" -ForegroundColor Green
        Write-Host ""
        
        # Obter URL do repositório
        $repoUrl = & "C:\Program Files\GitHub CLI\gh.exe" repo view --json url --jq '.url'
        Write-Host "🎉 Seu repositorio foi criado em:" -ForegroundColor Cyan
        Write-Host "   $repoUrl" -ForegroundColor White
        Write-Host ""
        
        Write-Host "🌐 Para ativar GitHub Pages (hospedagem web gratuita):" -ForegroundColor Cyan
        Write-Host "   1. Va para Settings no seu repositorio" -ForegroundColor White
        Write-Host "   2. Clique em Pages" -ForegroundColor White
        Write-Host "   3. Selecione 'Deploy from a branch'" -ForegroundColor White
        Write-Host "   4. Escolha 'main' branch" -ForegroundColor White
        Write-Host "   5. Clique Save" -ForegroundColor White
        Write-Host ""
        
        $username = & "C:\Program Files\GitHub CLI\gh.exe" api user --jq '.login'
        Write-Host "🚀 Sua agenda web estara disponivel em:" -ForegroundColor Cyan
        Write-Host "   https://$username.github.io/agenda-pessoal" -ForegroundColor White
        Write-Host ""
        
        # Abrir repositório no navegador
        Write-Host "Abrindo repositorio no navegador..." -ForegroundColor Yellow
        & "C:\Program Files\GitHub CLI\gh.exe" repo view --web
        
    } else {
        throw "Comando falhou com codigo $LASTEXITCODE"
    }
    
} catch {
    Write-Host ""
    Write-Host "❌ ERRO: Falha ao criar repositorio!" -ForegroundColor Red
    Write-Host "Detalhes do erro: $($_.Exception.Message)" -ForegroundColor Red
    Write-Host ""
    Write-Host "Verifique se:" -ForegroundColor Yellow
    Write-Host "- Voce esta logado no GitHub" -ForegroundColor White
    Write-Host "- O nome 'agenda-pessoal' nao existe" -ForegroundColor White
    Write-Host "- Voce tem permissoes para criar repositorios" -ForegroundColor White
    Write-Host "- Sua conexao com internet esta funcionando" -ForegroundColor White
}

Write-Host ""
Read-Host "Pressione Enter para sair"