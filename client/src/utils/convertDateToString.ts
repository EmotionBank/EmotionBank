import { DateType } from '@/types/date';

export const convertYYYYMM = (date: DateType) => String(date.year) + '-' + String(date.month);

export const convertYYYYMMDD = (date: DateType) =>
  String(date.year) + '-' + String(date.month) + '-' + String(date.date);
