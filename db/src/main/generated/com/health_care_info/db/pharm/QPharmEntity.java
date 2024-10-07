package com.health_care_info.db.pharm;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPharmEntity is a Querydsl query type for PharmEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPharmEntity extends EntityPathBase<PharmEntity> {

    private static final long serialVersionUID = 1248705653L;

    public static final QPharmEntity pharmEntity = new QPharmEntity("pharmEntity");

    public final com.health_care_info.db.QBaseEntity _super = new com.health_care_info.db.QBaseEntity(this);

    public final StringPath email = createString("email");

    public final StringPath hpId = createString("hpId");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public QPharmEntity(String variable) {
        super(PharmEntity.class, forVariable(variable));
    }

    public QPharmEntity(Path<? extends PharmEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPharmEntity(PathMetadata metadata) {
        super(PharmEntity.class, metadata);
    }

}

