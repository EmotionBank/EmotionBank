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
  emoticon: string;
  date: string;
  transactionType: string;
  title: string;
  amount: number;
}

// 거래내역
export interface TransactionListType {
  transactionInfoList: TransactionType[];
}

// 거래내역 상세조회
export interface TransactionDetailType {
  emoticon: string;
  amount: number;
  date: string;
  time: string;
  title: string;
  content: string;
  accountName: string; // 계좌명
  transactionType: string;
}

export interface CategoryType {
  categoryId: number;
  categoryName: string;
  visibility: string;
}
export interface CategoryListType {
  categoryInfoList: CategoryType[];
}
