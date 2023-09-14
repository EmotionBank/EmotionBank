export interface AccountBalanceType {
  accountName: string;
  balance: number;
}

export interface DepositTransactionType {
  balance: number;
}

export interface WithdrawTransactionType {
  balance: number;
}

export interface TransactionType {
  transactionId: number;
  emotion: string;
  date: Date;
  type: string;
  title: string;
  money: number;
  balance: number;
}

export interface TransactionListType {
  transactions: TransactionType[];
}

export interface TransactionDetailType {
  title: string;
  emotion: string;
  money: number;
  date: Date;
  comment: string;
  accountNumber: string;
  type: string;
}
