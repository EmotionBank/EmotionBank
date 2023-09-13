export interface AccountBalanceData {
  accountName: string;
  balance: number;
}

export interface DepositTransactionData {
  balance: number;
}

export interface WithdrawTransactionData {
  balance: number;
}

export interface TransactionData {
  transactionId: number;
  emotion: string;
  date: Date;
  type: string;
  title: string;
  money: number;
  balance: number;
}

export interface TransactionListData {
  transactions: TransactionData[];
}

export interface TransactionDetailData {
  title: string;
  emotion: string;
  money: number;
  date: Date;
  comment: string;
  accountNumber: string;
  type: string;
}
