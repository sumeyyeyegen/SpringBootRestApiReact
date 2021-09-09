import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from '../../../app/shared/error/error-boundary-route';

import ExamResult from './exam-result';
import ExamResultDetail from './exam-result-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ExamResultDetail} />
      <ErrorBoundaryRoute path={match.url} component={ExamResult} />
    </Switch>
  </>
);

export default Routes;
