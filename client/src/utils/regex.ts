export const setMoneyRegex = (money: string) => {
  if (money === '') return ''; // 빈 문자열 반환
  const numericValue = money.replace(/[^0-9.]/g, '');
  const parsedValue = parseFloat(numericValue);
  if (isNaN(parsedValue)) return ''; // NaN이면 빈 문자열 반환
  const formattedValue = parsedValue.toLocaleString('en-US');
  return formattedValue;
};

export const setAccountRegex = (account: string) => {
  return account
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{0,4})(\d{0,4})(\d{0,7})$/g, '$1-$2-$3')
    .replace(/(\-{1,2})$/g, '');
  // maxlength : 16
};

export const replaceDash = (str: string) => {
  return str.replace(/,/g, '');
};
