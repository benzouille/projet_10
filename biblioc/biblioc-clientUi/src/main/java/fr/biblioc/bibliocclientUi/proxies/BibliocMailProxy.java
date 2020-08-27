package fr.biblioc.bibliocclientUi.proxies;

import fr.biblioc.bibliocclientUi.beans.mail.PrereservationDispoDataBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Proxy du microservice reservation.
 */
@FeignClient(name = "biblioc-mail", url = "localhost:9007")
public interface BibliocMailProxy {

    @PostMapping(value = "/sendMailPrereservation")
    ResponseEntity sendMailPrereservation(@RequestBody PrereservationDispoDataBean prereservationDispoData);
}
