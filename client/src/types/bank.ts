export interface GetAccountBalanceTypeResponse {
  accountName: string;
  balance: number;
}

//계좌 입출금 인터페이스
export interface PostTransactionResponse {
  accountName: string;
  amount: number;
  balance: number;
  transactionType: string;
  content: string;
}

export interface TransactionResponse {
  transactionId: number;
  emoticon: string;
  date: string;
  transactionType: string;
  title: string;
  amount: number;
}

// 거래내역
export interface GetTransactionListResponse {
  transactionInfoList: TransactionResponse[];
}

// 거래내역 상세조회
export interface GetTransactionDetailResponse {
  emoticon: string;
  amount: number;
  date: string;
  time: string;
  title: string;
  content: string;
  accountName: string; // 계좌명
  transactionType: string;
}
export interface PostTransactionOtherResponse {
  receiver: string;
  amount: string;
  emoticon: string;
}

export interface CategoryResponse {
  categoryId: number;
  categoryName: string;
  visibility: string;
}
export interface GetCategoryListResponse {
  categoryInfoList: CategoryResponse[];
}
