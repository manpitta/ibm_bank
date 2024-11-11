export enum TipoConta {
    CORRENTE = 'CORRENTE',
    POUPANCA = 'POUPANCA'
}

export enum StatusConta {
    ATIVA = 'ATIVA',
    INATIVA = 'INATIVA'
}

export interface Conta {
    id: number;
    numero: number;
    tipoConta: TipoConta;
    saldo: number;
    status: StatusConta;
    clienteId?: number;
    nomeCliente?: string;
    contaCreditoId?: number;
}
