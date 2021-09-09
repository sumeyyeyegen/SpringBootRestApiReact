import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './exam-result.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const ExamResultDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const examResultEntity = useAppSelector(state => state.examResult.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="examResultDetailsHeading">
          <Translate contentKey="introApp.examResult.detail.title">ExamResult</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{examResultEntity.id}</dd>
          <dt>
            <span id="student_id">
              <Translate contentKey="introApp.examResult.student_id">Student Id</Translate>
            </span>
          </dt>
          <dd>{examResultEntity.student_id}</dd>
          <dt>
            <span id="score">
              <Translate contentKey="introApp.examResult.score">Score</Translate>
            </span>
          </dt>
          <dd>{examResultEntity.score}</dd>
          <dt>
            <span id="course_id">
              <Translate contentKey="introApp.examResult.course_id">Course Id</Translate>
            </span>
          </dt>
          <dd>{examResultEntity.course_id}</dd>
          <dt>
            <Translate contentKey="introApp.examResult.student">Student</Translate>
          </dt>
          <dd>{examResultEntity.student ? examResultEntity.student.id : ''}</dd>
          <dt>
            <Translate contentKey="introApp.examResult.course">Course</Translate>
          </dt>
          <dd>{examResultEntity.course ? examResultEntity.course.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/exam-result" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/exam-result/${examResultEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ExamResultDetail;
