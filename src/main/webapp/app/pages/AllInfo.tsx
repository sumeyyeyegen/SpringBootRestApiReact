import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useState } from 'react'
import { getSortState, JhiItemCount, JhiPagination, Translate } from 'react-jhipster';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Table } from 'reactstrap';
import { useAppDispatch, useAppSelector } from '../config/store';
import { getEntities } from '../entities/student/student.reducer';
import { overridePaginationStateWithQueryParams } from '../shared/util/entity-utils';
import { ASC, DESC, ITEMS_PER_PAGE, SORT } from '../shared/util/pagination.constants';

function AllInfo(props: RouteComponentProps<{ url: string }>) {
  const dispatch = useAppDispatch();

  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );

  const studentList = useAppSelector(state => state.student.entities);
  const examResultList = useAppSelector(state => state.examResult.entities);
  const courseList = useAppSelector(state => state.course.entities);
  const loading = useAppSelector(state => state.student.loading);
  const totalItems = useAppSelector(state => state.student.totalItems);

  
  

  const getAllEntities = () => {
    dispatch(
      getEntities({
        page: paginationState.activePage - 1,
        size: paginationState.itemsPerPage,
        sort: `${paginationState.sort},${paginationState.order}`,
      })
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?page=${paginationState.activePage}&sort=${paginationState.sort},${paginationState.order}`;
    if (props.location.search !== endURL) {
      props.history.push(`${props.location.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [paginationState.activePage, paginationState.order, paginationState.sort]);

  useEffect(() => {
    const params = new URLSearchParams(props.location.search);
    const page = params.get('page');
    const sort = params.get(SORT);
    if (page && sort) {
      const sortSplit = sort.split(',');
      setPaginationState({
        ...paginationState,
        activePage: +page,
        sort: sortSplit[0],
        order: sortSplit[1],
      });
    }
  }, [props.location.search]);

  const sort = p => () => {
    setPaginationState({
      ...paginationState,
      order: paginationState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handlePagination = currentPage =>
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });

  const handleSyncList = () => {
    sortEntities();
  };

  const { match } = props;
  return (
    <div>
      <h2 id="student-heading" data-cy="StudentHeading">
        <Translate contentKey="introApp.student.home.title">Students</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="introApp.student.home.refreshListLabel">Refresh List</Translate>
          </Button>
        </div>
      </h2>
      <div className="table-responsive">
        {studentList && studentList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('full_name')}>
                  <h6>Öğrenci İsmi</h6>
                </th>
                <th className="hand" onClick={sort('full_name')}>
                  <h6>Kurs İsmi</h6>
                </th>
                <th className="hand" onClick={sort('full_name')}>
                  <h6>Kurs Puanı</h6>
                </th>
                <th className="hand" onClick={sort('full_name')}>
                  <h6>Kurs Puanı</h6>
                </th>
                <th></th>
              </tr>
            </thead>
            <tbody>
                {
                examResultList.map((exam, i) => (
                  <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>{exam.student.full_name}</td>
                  <td>{exam.course.name}</td>
                  <td>{exam.score}</td>
                  <td>{exam.student.full_name}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${exam.student.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                  </tr>
              ))
                
              }
            </tbody>
            
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="introApp.student.home.notFound">No Students found</Translate>
            </div>
          )
        )}
      </div>
      {totalItems ? (
        <div className={studentList && studentList.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  )
}

export default AllInfo
