# Agenda Móvel - Aplicativo Android

Um aplicativo de agenda móvel para Android com botões verticais organizados por cores dos dias da semana.

## Funcionalidades

### ✨ Principais Características
- **Botões Verticais**: Interface com botões dispostos verticalmente para fácil navegação
- **Sistema de Cores**: 7 cores distintas para cada dia da semana
- **Detecção Automática**: Reconhece automaticamente o dia da semana no texto
- **Botões Arrastáveis**: Reorganize os itens arrastando e soltando
- **4 Sub-botões de Ação**: Cada item possui botões para diferentes ações

### 🎨 Sistema de Cores por Dia da Semana
- **Domingo**: Vermelho (#FF6B6B)
- **Segunda**: Azul claro (#4ECDC4)
- **Terça**: Azul (#45B7D1)
- **Quarta**: Verde claro (#96CEB4)
- **Quinta**: Amarelo (#FECA57)
- **Sexta**: Rosa (#FF9FF3)
- **Sábado**: Roxo (#6C5CE7)

### 🔧 Funcionalidades dos Sub-botões
1. **Mudança de Cor**: Permite alterar manualmente o dia da semana
2. **Editar**: Habilita a edição do texto do agendamento
3. **Salvar**: Salva o item e muda a cor para cinza, movendo para o início
4. **Apagar**: Remove o item e envia para a lixeira

## 📱 Como Usar

### Criando um Novo Agendamento
1. Toque no botão "+" (FAB) no canto superior direito
2. Digite seu agendamento no campo de texto
3. Se mencionar um dia da semana (ex: "segunda", "terça"), a cor será aplicada automaticamente

### Editando um Agendamento
1. Toque no item para editar o texto
2. Ou mantenha pressionado para ver os botões de ação
3. Use os sub-botões conforme necessário

### Organizando os Itens
- **Arrastar**: Mantenha pressionado e arraste para reordenar
- **Cores**: Itens são organizados automaticamente por cor/dia da semana
- **Salvos**: Itens salvos (cinza) aparecem no início da lista

## 🛠️ Instalação e Configuração

### Pré-requisitos
- Android Studio 4.0 ou superior
- SDK Android 21 (Android 5.0) ou superior
- Java 8 ou superior

### Passos para Instalação

1. **Clone ou baixe o projeto**
   ```bash
   # Se usando Git
   git clone [URL_DO_REPOSITORIO]
   ```

2. **Abra no Android Studio**
   - Abra o Android Studio
   - Selecione "Open an existing Android Studio project"
   - Navegue até a pasta do projeto

3. **Configurar dependências**
   - O arquivo `build.gradle` já contém todas as dependências necessárias
   - Sincronize o projeto (Sync Now)

4. **Executar o aplicativo**
   - Conecte um dispositivo Android ou inicie um emulador
   - Clique em "Run" ou pressione Shift+F10

### Estrutura do Projeto

```
Agenda Móvel/
├── AndroidManifest.xml          # Configurações do app
├── build.gradle                 # Dependências e configurações
├── MainActivity.java            # Atividade principal
├── AgendaItem.java             # Modelo de dados
├── AgendaAdapter.java          # Adapter do RecyclerView
├── activity_main.xml           # Layout principal
├── item_agenda.xml             # Layout dos itens
├── strings.xml                 # Strings do app
├── styles.xml                  # Estilos e temas
└── drawable/                   # Recursos gráficos
    ├── agenda_item_background.xml
    ├── action_button_background.xml
    ├── color_button_selector.xml
    └── drag_handle.xml
```

## 🎯 Detalhes Técnicos

### Arquitetura
- **Padrão MVC**: Model (AgendaItem), View (XML layouts), Controller (MainActivity)
- **RecyclerView**: Para lista eficiente e arrastável
- **CardView**: Para design moderno dos itens
- **Material Design**: Seguindo diretrizes do Google

### Funcionalidades Avançadas
- **Detecção de Texto**: Algoritmo que identifica dias da semana no texto
- **Organização Automática**: Classificação por cores e status de salvamento
- **Lixeira**: Sistema de recuperação de itens apagados
- **Persistência**: Data e hora de criação de cada agendamento

## 🐛 Solução de Problemas

### Problemas Comuns

1. **App não compila**
   - Verifique se todas as dependências estão sincronizadas
   - Limpe o projeto: Build → Clean Project

2. **Cores não aparecem**
   - Verifique se os arquivos drawable estão na pasta correta
   - Confirme que os recursos estão sendo referenciados corretamente

3. **Arrastar não funciona**
   - Verifique se o ItemTouchHelper está configurado
   - Confirme que o RecyclerView tem o layout manager correto

## 📄 Licença

Este projeto é de código aberto e está disponível sob a licença MIT.

## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para:
- Reportar bugs
- Sugerir novas funcionalidades
- Enviar pull requests
- Melhorar a documentação

---

**Desenvolvido com ❤️ para organizar sua agenda de forma intuitiva e colorida!**