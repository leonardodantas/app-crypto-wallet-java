package com.crypto.wallet.infra.feign;

import com.crypto.wallet.infra.feign.json.TickerRestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://www.mercadobitcoin.net/api", name = "get.last.day.summary")
public interface FindLastDayCryptocurrencySummaryFeign {

    @GetMapping("/{name}/ticker")
    TickerRestDTO getSummary(@PathVariable String name);
}
