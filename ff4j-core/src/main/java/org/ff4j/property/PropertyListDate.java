package org.ff4j.property;

/*-
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2018 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.util.Date;
import java.util.Iterator;

/**
 * Load property as list of {@link Date}.
 *
 * @author Cedrick LUNVEN (@clunven)
 */
public class PropertyListDate extends PropertyList<Date, PropertyDate > {
     
    /** Serial Number. */
    private static final long serialVersionUID = 3088617073441400442L;
    
   
    public PropertyListDate(String uid, String valueAsString) {
        super(uid, valueAsString);
    }
   
    public PropertyListDate(String uid, Date  ... value) {
        super(uid, value);
    }
    
    /** {@inheritDoc} */
    @Override
    public String getValueAsString() {
        if (get() == null) return null;
        Iterator<Date> it = get().iterator();
        StringBuilder sb = new StringBuilder(SIMPLE_DATE_FORMAT.format(it.next().getTime()));
        while (it.hasNext()) {
            sb.append(listDelimiter);
            sb.append(SIMPLE_DATE_FORMAT.format(it.next().getTime()));
        }
        return sb.toString();
    }
  

}
