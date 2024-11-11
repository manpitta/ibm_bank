export enum StatusContaCredito {
    ATIVA = 'ATIVA',
    INATIVA = 'INATIVA'
}

export interface ContaCredito {
    id: number;
    contaId: number;
    limiteCredito: number;
    saldoUtilizado: number;
    dataFechamento: string;
    dataPagamento: string;
    status: StatusContaCredito;
}

export interface ContaCreditoToCreate {
    limiteCredito: number;
    dataFechamento: string;
    dataPagamento: string;
}

