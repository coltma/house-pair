package demo.model.impl;

import com.mongodb.WriteResult;
import demo.model.HouseData;
import demo.model.HouseDataRepository;
import demo.model.HouseDataRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

public class HouseDataRepositoryImpl implements HouseDataRepositoryCustom {

    @Autowired
    private MongoOperations mongoOps;

    /**
     * same house: location, houseNumber, bedroom, bathroom, areaSize
     */
    @Override
    public HouseData findSameHouse(HouseData current) {
        Point point = new Point(current.getLocation().getX(), current.getLocation().getY());
        HouseData last = mongoOps.findOne(
            Query.query(Criteria.where("location").nearSphere(point).maxDistance(0).minDistance(0)
            .andOperator(
                Criteria.where("houseNumber").is(current.getHouseNumber())
                .andOperator(
                    Criteria.where("bedroom").is(current.getBedroom())
                    .andOperator(
                        Criteria.where("bathroom").is(current.getBathroom())
                        .andOperator(
                                Criteria.where("areaSize").is(current.getAreaSize())
                        )
                    )
                )
        )), HouseData.class);
        return last;
    }

    /**
     * Update all, since its link may change.
     * @param old
     * @param current
     */
    @Override
    public void update(HouseData old, HouseData current) {
        mongoOps.remove(old);
        mongoOps.save(current);
//        WriteResult wr = mongoOps.updateFirst(Query.query(Criteria.where("_id").is(old.getId())),
//                new Update().set("price", current.getPrice()),
//                HouseData.class);
    }
}
