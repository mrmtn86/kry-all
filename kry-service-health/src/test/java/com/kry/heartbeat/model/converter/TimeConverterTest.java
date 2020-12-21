package com.kry.heartbeat.model.converter;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class TimeConverterTest {

    @Test
    void testToStringShouldReturnToString() {
        String result = TimeConverter.toString(Instant.ofEpochMilli(12345679L));

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("1970-01-01 03:25:45");
    }

    @Test
    void testToStringShouldReturnNullWhenNullPassed() {
        assertThat(TimeConverter.toString(null)).isNull();
    }
}
