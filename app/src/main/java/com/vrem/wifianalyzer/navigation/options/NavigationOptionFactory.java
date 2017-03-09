/*
 * WiFiAnalyzer
 * Copyright (C) 2017  VREM Software Development <VREMSoftwareDevelopment@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.vrem.wifianalyzer.navigation.options;

import java.util.Arrays;
import java.util.List;

public class NavigationOptionFactory {
    public final static NavigationOption WIFI_SWITCH_ON = new WiFiSwitchOn();
    public final static NavigationOption SCANNER_SWITCH_ON = new ScannerSwitchOn();
    public final static NavigationOption FILTER_ON = new FilterOn();
    public final static NavigationOption NEXT_PREV_ON = new NextPrevNavigationOn();

    public final static NavigationOption WIFI_SWITCH_OFF = new WiFiSwitchOff();
    public final static NavigationOption SCANNER_SWITCH_OFF = new ScannerSwitchOff();
    public final static NavigationOption FILTER_OFF = new FilterOff();
    public final static NavigationOption NEXT_PREV_OFF = new NextPrevNavigationOff();

    public final static List<NavigationOption> AP = Arrays.asList(WIFI_SWITCH_OFF, SCANNER_SWITCH_ON, FILTER_ON, NEXT_PREV_ON);
    public final static List<NavigationOption> OTHER = Arrays.asList(WIFI_SWITCH_ON, SCANNER_SWITCH_ON, FILTER_OFF, NEXT_PREV_ON);
    public final static List<NavigationOption> OFF = Arrays.asList(WIFI_SWITCH_OFF, SCANNER_SWITCH_OFF, FILTER_OFF, NEXT_PREV_OFF);
}
