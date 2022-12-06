package com.snofty.datadogmetrics.rest;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DatadogMetricsController {

    private AtomicLong counter = new AtomicLong();

    @Autowired
    private MeterRegistry meterRegistry;

    @PostMapping(path = "/publish")
    public void publish() {
        List<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("type", "snofty"));
        int count = 1;
        meterRegistry.counter("snofty.testing.datadog.metrics", tags)
                .increment(Optional.of(count).orElse(1));
        counter.addAndGet(count);
    }

    @GetMapping(path = "/counter")
    public long getCounter() {
        return counter.get();
    }
}
