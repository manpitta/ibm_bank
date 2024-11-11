export enum TipoMovimentacao {
    DEBITO = 'DEBITO',
    CREDITO = 'CREDITO'
}

export enum StatusPagamento {
    PENDENTE = 'PENDENTE',
    PAGO = 'PAGO'
}
export interface Movimentacao {
    id: number;
    contaId?: number; 
    contaCreditoId?: number; 
    tipo: TipoMovimentacao; 
    valor: number; 
    dataMovimentacao: string; 
    descricao: string; 
    statusPagamento?: StatusPagamento; 
    dataCompetencia?: string; 
}

export enum eColunaXPropMovimentacao {
    // 'Nome da coluna' = 'nome da propriedade'
    'ID' = 'id',
    'Data da Movimentação' = 'dataMovimentacao',
    'Tipo' = 'tipo',
    'Valor' = 'valor',
    'Status do Pagamento' = 'statusPagamento',
    'Data de Competência' = 'dataCompetencia',
    'Conta ID' = 'contaId',
    'Conta Crédito ID' = 'contaCreditoId',
  }