/*
 * Copyright (c) 2008-2021 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/commercial-software-license for details.
 */

package com.company.thesissummer;


import com.haulmont.thesis.testsupport.ThesisTestContainer;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ThesissummerTestContainer extends ThesisTestContainer {

    public ThesissummerTestContainer() {
        super();
        // project and special properties for test environment.
        appPropertiesFiles.add("thesissummer-app.properties");
        appPropertiesFiles.add("thesissummer-test-app.properties");
        autoConfigureDataSource();
    }

    public static class Common extends ThesissummerTestContainer {

        // A common singleton instance of the test container which is initialized once for all tests
        public static final ThesissummerTestContainer.Common INSTANCE = new ThesissummerTestContainer.Common();

        private static volatile boolean initialized;

        private Common() {}

        @Override
        public void beforeAll(ExtensionContext extensionContext) throws Exception {
            if (!initialized) {
                super.beforeAll(extensionContext);
                initialized = true;
            }
            setupContext();
        }

        @SuppressWarnings("RedundantThrows")
        @Override
        public void afterAll(ExtensionContext extensionContext) throws Exception {
            cleanupContext();
           // never stops - do not call super
        }
    }
}