/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package bugs

import org.junit.Test

import static groovy.test.GroovyAssert.assertScript
import static groovy.test.GroovyAssert.shouldFail

final class Groovy11263 {

    @Test
    void testUnreachableStatementAfterReturn1() {
        def err = shouldFail '''\
            def m() {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn2() {
        def err = shouldFail '''\
            class X {
                X() {
                    return
                    def a = 1
                }
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 21\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn3() {
        def err = shouldFail '''\
            {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn4() {
        def err = shouldFail '''\
            for (var i in [1, 2, 3]) {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn5() {
        def err = shouldFail '''\
            while (true) {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn6() {
        def err = shouldFail '''\
            do {
                return
                def a = 1
            } while (true)
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn7() {
        def err = shouldFail '''\
            if (true) {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn8() {
        def err = shouldFail '''\
            if (true) {
            } else {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn9() {
        def err = shouldFail '''\
            try {
                return
                def a = 1
            } catch (e) {
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn10() {
        def err = shouldFail '''\
            try {
            } catch (e) {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn11() {
        def err = shouldFail '''\
            try {
            } finally {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn12() {
        def err = shouldFail '''\
            switch(1) {
                case 1:
                    return
                    def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 21\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn13() {
        def err = shouldFail '''\
            switch(1) {
                default:
                    return
                    def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 4, column 21\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn14() {
        def err = shouldFail '''\
            [1, 2, 3].each {
                return
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterReturn15() {
        def err = shouldFail '''\
            [1, 2, 3].each(e -> {
                return
                def a = 1
            })
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterBreak() {
        def err = shouldFail '''\
            for (var i in [1, 2, 3]) {
                break
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }

    @Test
    void testUnreachableStatementAfterContinue() {
        def err = shouldFail '''\
            for (var i in [1, 2, 3]) {
                continue
                def a = 1
            }
        '''

        assert err ==~ /(?s)(.+)\s+Unreachable statement found\s+@ line 3, column 17\.\s+def a = 1\s+(.+)/
    }
}
