const appName = 'IBM Bank';

export const environment = {

    production: false,
    appName: appName,
    pagesTitle: {
        '/clientes': appName +' | Página Inicial',

        '/clientes/novo': appName +' | Novo cliente',
        '/clientes/editar': appName +' | Editar cliente',

        '/movimentacoes': appName +' | Movimentações',
        '/movimentacoes/novo': appName +' | Nova movimentação',

    } as {[key: string]: string},
    IBMBankApi: {
        baseUrl: 'http://localhost:8080',
        endpoints: {
            clientes: 'clientes',
            movimentacoes: 'movimentacoes',
            contas: 'contas',
            contasCredito: 'contasCredito',
        }
    },

}

export const baseEnvironment = environment;
