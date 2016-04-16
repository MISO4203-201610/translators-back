package co.edu.uniandes.csw.translationservice.ejbs;

import co.edu.uniandes.csw.translationservice.api.IReviewLogic;
import co.edu.uniandes.csw.translationservice.entities.ReviewEntity;
import co.edu.uniandes.csw.translationservice.persistence.ReviewPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @generated
 */
@Stateless
public class ReviewLogic implements IReviewLogic {

    @Inject private ReviewPersistence persistence;

    /**
     * @generated
     */
    @Override
    public int countReviews() {
        return persistence.count();
    }

    /**
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews() {
        return persistence.findAll();
    }

    /**
     * @generated
     */
    @Override
    public List<ReviewEntity> getReviews(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }
    /**
     * @generated
     */
    @Override
    public ReviewEntity getReview(Long id) {
        return persistence.find(id);
    }

    /**
     * @generated
     */
    @Override
    public ReviewEntity createReview(ReviewEntity entity) {
        persistence.create(entity);
        return entity;
    }

    /**
     * @generated
     */
    @Override
    public ReviewEntity updateReview(ReviewEntity entity) {
        ReviewEntity newEntity = entity;
        return persistence.update(newEntity);
    }

    /**
     * @generated
     */
    @Override
    public void deleteReview(Long id) {
        persistence.delete(id);
    }
}
