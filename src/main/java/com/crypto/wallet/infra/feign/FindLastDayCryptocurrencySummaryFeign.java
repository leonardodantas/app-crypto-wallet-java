package com.crypto.wallet.infra.feign;

import com.crypto.wallet.infra.feign.json.TickerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://www.mercadobitcoin.net/api", name = "get.last.day.summary")
public interface FindLastDayCryptocurrencySummaryFeign {

    @GetMapping("/{name}/ticker")
    TickerResponse getSummary(@PathVariable final String name);
}
