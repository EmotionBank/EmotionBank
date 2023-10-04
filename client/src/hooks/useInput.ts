import { useState } from 'react';

export const useInput = (initValue: string) => {
  const [value, setValue] = useState(initValue);

  const handleInput = (event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setValue(event.target.value);
  };

  return [value, handleInput] as const;
};
