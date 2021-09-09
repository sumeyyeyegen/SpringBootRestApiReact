import { IStudent } from 'app/shared/model/student.model';

export interface ICourse {
  id?: number;
  name?: string | null;
  students?: IStudent[] | null;
}

export const defaultValue: Readonly<ICourse> = {};
