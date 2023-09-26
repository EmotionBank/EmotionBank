export interface AccountBalanceType {
  accountName: string;
  balance: number;
}

//계좌 입출금 인터페이스
export interface DepositTransactionType {
  accountName: string;
  amount: number;
  balance: number;
  transactionType: string;
  content: string;
}

export interface TransactionType {
  transactionId: number;
  emotion: string;
  date: string;
  transactionType: string;
  title: string;
  amount: number;
}

// 거래내역
export interface TransactionListType {
  transactions: TransactionType[];
}

// 거래내역 상세조회
export interface TransactionDetailType {
  emotion: string;
  amount: number;
  date: Date | string;
  time: string;
  title: string;
  content: string;
  accountName: string; // 계좌명
  transactionType: string;
}