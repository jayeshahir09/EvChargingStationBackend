package com.example.EvChargingProjectBackend.specification;

import com.example.EvChargingProjectBackend.entity.Charger;
import com.example.EvChargingProjectBackend.entity.ChargingStation;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ChargingStationSpecification {
    public static Specification<ChargingStation> city(String city){
        return (root,query,cb)->
                (city==null || city.isBlank() ? null : cb.equal(cb.lower(root.get("city")),city.toLowerCase()));
    }

    public static Specification<ChargingStation> connectorType(String connectorType){
        return (root,query,cb)-> {
            if(connectorType == null){
                return null;
            }
            Join<ChargingStation, Charger> chargerJoin =  root.join("chargerList");
            return cb.equal(chargerJoin.get("connectorType"),connectorType);
        };
    }
    public static Specification<ChargingStation> chargerType(String chargerType){
        return (root,query,cb)-> {
            if(chargerType == null){
                return null;
            }
            Join<ChargingStation, Charger> chargerJoin =  root.join("chargerList");
            return cb.equal(chargerJoin.get("chargerType"),chargerType);
        };
    }
    public static Specification<ChargingStation> search(String words){
        return (root,query,cb)-> {
            if (words == null || words.isEmpty()) {
                return null;
            }
            String pattern = "%" + words.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("stationName")), pattern),
                    cb.like(cb.lower(root.get("address")), pattern),
                    cb.like(cb.lower(root.get("city")), pattern),
                    cb.like(cb.lower(root.get("state")),pattern)
            );
        };
    }
}
