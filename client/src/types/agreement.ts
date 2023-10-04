export interface GetAgreementListResponse {
  agreements: AgreementResponse[];
}

export interface AgreementResponse {
  agreementId: number;
  termsId: number;
  title: string;
  content: string;
  mandatory: string;
  state: string;
  agreementTime: string;
}
