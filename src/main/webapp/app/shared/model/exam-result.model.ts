import { IStudent } from 'app/shared/model/student.model';
import { ICourse } from 'app/shared/model/course.model';

export interface IExamResult {
  id?: number;
  student_id?: number | null;
  score?: number | null;
  course_id?: number | null;
  student?: IStudent | null;
  course?: ICourse | null;
}

export const defaultValue: Readonly<IExamResult> = {};
