package com.ag04.utils.shell.table.ascii;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.vandermeer.asciitable.AsciiTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Displays entity as table.
 *
 * @author dmadunic on 26.07.2018
 */
public class AsciiTableRenderer {
    private static final Logger LOG = LoggerFactory.getLogger(AsciiTableRenderer.class);

   private ObjectMapper objectMapper;

   public AsciiTableRenderer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
   }

    public String entityTable(Object entity, String tableHeader) {
        Map<String, String> map = objectMapper.convertValue(entity, new TypeReference<Map<String, String>>() {});

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow(null, tableHeader);
        at.addRule();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String value = entry.getValue();
            if (value == null) {
                value = "null";
            }
            at.addRow(entry.getKey(), value);
            at.addRule();
        }
        String table = at.render();
        return table + "\n";
    }

}
