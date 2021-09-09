export interface IStudent {
  id?: number;
  number?: number | null;
  gsm_number?: string | null;
  full_name?: string | null;
  email?: string | null;
}

export const defaultValue: Readonly<IStudent> = {};
