# 🚀 Guia Rápido: Criar Repositório no GitHub

## ✅ Seu projeto está pronto!

✅ **Git configurado** - Repositório local criado  
✅ **Arquivos commitados** - 23 arquivos versionados  
✅ **Scripts prontos** - Automação disponível  

## 🎯 Opção 1: Método Automático (Recomendado)

### Execute um dos scripts:

**Windows (Duplo clique):**
```
criar_repositorio_github.bat
```

**PowerShell:**
```powershell
.\criar_repositorio_github.ps1
```

### O script fará:
1. ✅ Verificar GitHub CLI
2. 🔐 Fazer login no GitHub (se necessário)
3. 📦 Criar repositório "agenda-pessoal"
4. ⬆️ Fazer upload dos arquivos
5. 🌐 Abrir no navegador

---

## 🎯 Opção 2: Método Manual

### 1. Criar repositório no GitHub.com

1. Acesse [github.com](https://github.com) e faça login
2. Clique **"New"** ou **"+"** → **"New repository"**
3. Configure:
   - **Nome:** `agenda-pessoal`
   - **Descrição:** `Agenda pessoal com sistema de cores automático - Versão Web e Android`
   - **Público** ou **Privado** (sua escolha)
   - **NÃO marque** "Initialize with README"
4. Clique **"Create repository"**

### 2. Conectar e enviar arquivos

No terminal (PowerShell), execute:

```powershell
# Adicionar repositório remoto (SUBSTITUA SEU_USUARIO)
git remote add origin https://github.com/SEU_USUARIO/agenda-pessoal.git

# Renomear branch para main
git branch -M main

# Enviar arquivos
git push -u origin main
```

### 3. Autenticação

Quando solicitado usuário/senha:
- **Usuário:** Seu username do GitHub
- **Senha:** Token de acesso pessoal (não a senha da conta)

**Para criar token:**
1. GitHub → **Settings** → **Developer settings** → **Personal access tokens** → **Tokens (classic)**
2. **"Generate new token (classic)"**
3. Marque **"repo"** (acesso completo)
4. Copie o token e use como senha

---

## 🌐 Ativar GitHub Pages (Hospedagem Gratuita)

Após criar o repositório:

1. No repositório: **Settings** → **Pages**
2. **Source:** Deploy from a branch
3. **Branch:** main / (root)
4. **Save**
5. Aguarde alguns minutos
6. Acesse: `https://SEU_USUARIO.github.io/agenda-pessoal`

---

## 📁 O que será enviado

```
📦 agenda-pessoal/
├── 🌐 VERSÃO WEB
│   ├── index.html
│   ├── styles.css
│   ├── script.js
│   └── COMO_USAR.md
│
├── 📱 VERSÃO ANDROID
│   ├── MainActivity.java
│   ├── AgendaAdapter.java
│   ├── *.xml (layouts)
│   └── build.gradle
│
├── 📋 DOCUMENTAÇÃO
│   ├── README_GITHUB.md
│   ├── INSTRUCOES_GITHUB.md
│   └── GUIA_RAPIDO_GITHUB.md
│
└── 🔧 AUTOMAÇÃO
    ├── criar_repositorio_github.bat
    └── criar_repositorio_github.ps1
```

---

## ❓ Resolução de Problemas

### Erro de autenticação:
- Use token de acesso em vez de senha
- Verifique permissões do token

### Erro "repository exists":
- Escolha outro nome ou delete o existente

### GitHub CLI não funciona:
- Use o método manual
- Reinstale: `winget install GitHub.cli`

---

## 🎉 Resultado Final

Após o upload você terá:

✅ **Repositório público** no GitHub  
✅ **Código fonte completo** versionado  
✅ **Documentação profissional**  
✅ **Backup seguro** na nuvem  
✅ **Link para compartilhar**  
✅ **Hospedagem web gratuita** (GitHub Pages)  

**🚀 Sua agenda estará disponível para o mundo todo!**