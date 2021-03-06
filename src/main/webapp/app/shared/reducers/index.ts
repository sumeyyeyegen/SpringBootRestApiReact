import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from '../../../app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from '../../../app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from '../../../app/modules/account/register/register.reducer';
import activate, { ActivateState } from '../../../app/modules/account/activate/activate.reducer';
import password, { PasswordState } from '../../../app/modules/account/password/password.reducer';
import settings, { SettingsState } from '../../../app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from '../../../app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import student from '../../../app/entities/student/student.reducer';
// prettier-ignore
import course from '../../../app/entities/course/course.reducer';
// prettier-ignore
import examResult from '../../../app/entities/exam-result/exam-result.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const rootReducer = {
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  student,
  course,
  examResult,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
};

export default rootReducer;
