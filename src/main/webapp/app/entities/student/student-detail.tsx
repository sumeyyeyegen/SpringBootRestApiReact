import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './student.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from '../../../app/config/constants';
import { useAppDispatch, useAppSelector } from '../../../app/config/store';

export const StudentDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const studentEntity = useAppSelector(state => state.student.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="studentDetailsHeading">
          <Translate contentKey="introApp.student.detail.title">Student</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{studentEntity.id}</dd>
          <dt>
            <span id="number">
              <Translate contentKey="introApp.student.number">Number</Translate>
            </span>
          </dt>
          <dd>{studentEntity.number}</dd>
          <dt>
            <span id="gsm_number">
              <Translate contentKey="introApp.student.gsm_number">Gsm Number</Translate>
            </span>
          </dt>
          <dd>{studentEntity.gsm_number}</dd>
          <dt>
            <span id="full_name">
              <Translate contentKey="introApp.student.full_name">Full Name</Translate>
            </span>
          </dt>
          <dd>{studentEntity.full_name}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="introApp.student.email">Email</Translate>
            </span>
          </dt>
          <dd>{studentEntity.email}</dd>
        </dl>
        <Button tag={Link} to="/student" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/student/${studentEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StudentDetail;
