import { DateType } from '@/types/date';

const addZero = (num: string) => {
  if (num.length === 1) return '0' + num;
  return num;
};

export const convertYYYYMM = (date: DateType) => String(date.year) + '-' + addZero(String(date.month));

export const convertYYYYMMDD = (date: DateType) =>
  String(date.year) + '-' + addZero(String(date.month)) + '-' + addZero(String(date.date));
