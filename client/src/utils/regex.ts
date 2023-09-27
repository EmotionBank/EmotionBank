export const setMoneyRegex = (money: number) => {
  return money.toLocaleString();
};

export const setAccountRegex = (account: string) => {
  return account
    .replace(/[^0-9]/g, '')
    .replace(/^(\d{0,4})(\d{0,4})(\d{0,7})$/g, '$1-$2-$3')
    .replace(/(\-{1,2})$/g, '');
  // maxlength : 16
};

export const replaceDash = (str: string) => {
  return str.replace(/-/g, '');
};
