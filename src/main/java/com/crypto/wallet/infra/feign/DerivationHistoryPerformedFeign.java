package com.crypto.wallet.infra.feign;

import com.crypto.wallet.infra.feign.json.DerivationHistoryPerformedResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://www.mercadobitcoin.net/api", name = "get.derivation.history.performed")
public interface DerivationHistoryPerformedFeign {

    @GetMapping("/{name}/trades")
    List<DerivationHistoryPerformedResponse> getDerivationHistoryPerformed(@PathVariable String name);
}
