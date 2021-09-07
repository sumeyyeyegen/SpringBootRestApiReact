import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Student from './student';
import StudentDetail from './student-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StudentDetail} />
      <ErrorBoundaryRoute path={match.url} component={Student} />
    </Switch>
  </>
);

export default Routes;
