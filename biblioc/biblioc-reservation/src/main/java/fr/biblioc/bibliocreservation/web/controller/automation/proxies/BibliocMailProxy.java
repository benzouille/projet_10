package fr.biblioc.bibliocreservation.web.controller.automation.proxies;


import fr.biblioc.bibliocreservation.model.PrereservationDispoDataBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Proxy du microservice reservation.
 */
@FeignClient(name = "biblioc-mail", url = "localhost:9007")
public interface BibliocMailProxy {

    @PostMapping(value = "/sendMailListPrereservation")
    public ResponseEntity sendMailPrereservationList(@RequestBody List<PrereservationDispoDataBean> prereservationDispoDatas);
}
