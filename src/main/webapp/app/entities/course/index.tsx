import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Course from './course';
import CourseDetail from './course-detail';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CourseDetail} />
      <ErrorBoundaryRoute path={match.url} component={Course} />
    </Switch>
  </>
);

export default Routes;
