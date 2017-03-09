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

package com.vrem.wifianalyzer.settings;

import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import com.vrem.util.EnumUtils;
import com.vrem.wifianalyzer.R;
import com.vrem.wifianalyzer.navigation.NavigationMenu;
import com.vrem.wifianalyzer.wifi.AccessPointView;
import com.vrem.wifianalyzer.wifi.ConnectionViewType;
import com.vrem.wifianalyzer.wifi.band.WiFiBand;
import com.vrem.wifianalyzer.wifi.graph.tools.GraphLegend;
import com.vrem.wifianalyzer.wifi.model.GroupBy;
import com.vrem.wifianalyzer.wifi.model.Security;
import com.vrem.wifianalyzer.wifi.model.SortBy;
import com.vrem.wifianalyzer.wifi.model.Strength;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SettingsTest {
    @Mock
    private Repository repository;
    @Mock
    private Context context;
    @Mock
    private Resources resources;
    @Mock
    private Configuration configuration;
    @Mock
    private OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;

    private Settings fixture;

    @Before
    public void setUp() {
        fixture = new Settings(context);
        fixture.setRepository(repository);
    }

    @Test
    public void testInitializeDefaultValues() throws Exception {
        fixture.initializeDefaultValues();
        verify(repository).initializeDefaultValues();
    }

    @Test
    public void testRegisterOnSharedPreferenceChangeListener() throws Exception {
        // execute
        fixture.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        // validate
        verify(repository).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Test
    public void testGetScanInterval() throws Exception {
        // setup
        int defaultValue = 10;
        int expected = 11;
        when(repository.getResourceInteger(R.integer.scan_interval_default)).thenReturn(defaultValue);
        when(repository.getInteger(R.string.scan_interval_key, defaultValue)).thenReturn(expected);
        // execute
        int actual = fixture.getScanInterval();
        // validate
        assertEquals(expected, actual);
        verify(repository).getResourceInteger(R.integer.scan_interval_default);
        verify(repository).getInteger(R.string.scan_interval_key, defaultValue);
    }

    @Test
    public void testGetGroupBy() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.group_by_key, GroupBy.NONE.ordinal())).thenReturn(GroupBy.CHANNEL.ordinal());
        // execute
        GroupBy actual = fixture.getGroupBy();
        // validate
        assertEquals(GroupBy.CHANNEL, actual);
        verify(repository).getStringAsInteger(R.string.group_by_key, GroupBy.NONE.ordinal());
    }

    @Test
    public void testGetSortBy() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.sort_by_key, SortBy.STRENGTH.ordinal())).thenReturn(SortBy.SSID.ordinal());
        // execute
        SortBy actual = fixture.getSortBy();
        // validate
        assertEquals(SortBy.SSID, actual);
        verify(repository).getStringAsInteger(R.string.sort_by_key, SortBy.STRENGTH.ordinal());
    }

    @Test
    public void testGetAccessPointView() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.ap_view_key, AccessPointView.COMPLETE.ordinal())).thenReturn(AccessPointView.COMPACT.ordinal());
        // execute
        AccessPointView actual = fixture.getAccessPointView();
        // validate
        assertEquals(AccessPointView.COMPACT, actual);
        verify(repository).getStringAsInteger(R.string.ap_view_key, AccessPointView.COMPLETE.ordinal());
    }

    @Test
    public void testGetConnectionViewType() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.connection_view_key, ConnectionViewType.COMPLETE.ordinal())).thenReturn(ConnectionViewType.COMPACT.ordinal());
        // execute
        ConnectionViewType actual = fixture.getConnectionViewType();
        // validate
        assertEquals(ConnectionViewType.COMPACT, actual);
        verify(repository).getStringAsInteger(R.string.connection_view_key, ConnectionViewType.COMPLETE.ordinal());
    }

    @Test
    public void testGetThemeStyle() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.theme_key, ThemeStyle.DARK.ordinal())).thenReturn(ThemeStyle.LIGHT.ordinal());
        // execute
        ThemeStyle actual = fixture.getThemeStyle();
        // validate
        assertEquals(ThemeStyle.LIGHT, actual);
        verify(repository).getStringAsInteger(R.string.theme_key, ThemeStyle.DARK.ordinal());
    }

    @Test
    public void testGetGraphMaximumY() throws Exception {
        // setup
        int defaultValue = 1;
        int value = 2;
        int expected = value * Settings.GRAPH_Y_MULTIPLIER;
        when(repository.getStringAsInteger(R.string.graph_maximum_y_default, Settings.GRAPH_Y_DEFAULT)).thenReturn(defaultValue);
        when(repository.getStringAsInteger(R.string.graph_maximum_y_key, defaultValue)).thenReturn(value);
        // execute
        int actual = fixture.getGraphMaximumY();
        // validate
        assertEquals(expected, actual);
        verify(repository).getStringAsInteger(R.string.graph_maximum_y_default, Settings.GRAPH_Y_DEFAULT);
        verify(repository).getStringAsInteger(R.string.graph_maximum_y_key, defaultValue);
    }

    @Test
    public void testGetChannelGraphLegend() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.channel_graph_legend_key, GraphLegend.HIDE.ordinal())).thenReturn(GraphLegend.RIGHT.ordinal());
        // execute
        GraphLegend actual = fixture.getChannelGraphLegend();
        // validate
        assertEquals(GraphLegend.RIGHT, actual);
        verify(repository).getStringAsInteger(R.string.channel_graph_legend_key, GraphLegend.HIDE.ordinal());
    }

    @Test
    public void testGetTimeGraphLegend() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.time_graph_legend_key, GraphLegend.LEFT.ordinal())).thenReturn(GraphLegend.RIGHT.ordinal());
        // execute
        GraphLegend actual = fixture.getTimeGraphLegend();
        // validate
        assertEquals(GraphLegend.RIGHT, actual);
        verify(repository).getStringAsInteger(R.string.time_graph_legend_key, GraphLegend.LEFT.ordinal());
    }

    @Test
    public void testGetWiFiBand() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.wifi_band_key, WiFiBand.GHZ2.ordinal())).thenReturn(WiFiBand.GHZ5.ordinal());
        // execute
        WiFiBand actual = fixture.getWiFiBand();
        // validate
        assertEquals(WiFiBand.GHZ5, actual);
        verify(repository).getStringAsInteger(R.string.wifi_band_key, WiFiBand.GHZ2.ordinal());
    }

    @Test
    public void testGetWiFiBandFilter() throws Exception {
        // setup
        WiFiBand expected = WiFiBand.GHZ5;
        Set<String> values = Collections.singleton("" + expected.ordinal());
        Set<String> defaultValues = EnumUtils.ordinals(WiFiBand.class);
        when(repository.getStringSet(R.string.filter_wifi_band_key, defaultValues)).thenReturn(values);
        // execute
        Set<WiFiBand> actual = fixture.getWiFiBandFilter();
        // validate
        assertEquals(1, actual.size());
        assertTrue(actual.contains(expected));
        verify(repository).getStringSet(R.string.filter_wifi_band_key, defaultValues);
    }

    @Test
    public void testSaveWiFiBandFilter() throws Exception {
        // setup
        Set<WiFiBand> values = Collections.singleton(WiFiBand.GHZ5);
        Set<String> expected = Collections.singleton("" + WiFiBand.GHZ5.ordinal());
        // execute
        fixture.saveWiFiBandFilter(values);
        // validate
        verify(repository).saveStringSet(R.string.filter_wifi_band_key, expected);
    }

    @Test
    public void testGetStrengthFilter() throws Exception {
        // setup
        Strength expected = Strength.THREE;
        Set<String> values = Collections.singleton("" + expected.ordinal());
        Set<String> defaultValues = EnumUtils.ordinals(Strength.class);
        when(repository.getStringSet(R.string.filter_strength_key, defaultValues)).thenReturn(values);
        // execute
        Set<Strength> actual = fixture.getStrengthFilter();
        // validate
        assertEquals(1, actual.size());
        assertTrue(actual.contains(expected));
        verify(repository).getStringSet(R.string.filter_strength_key, defaultValues);
    }

    @Test
    public void testSaveStrengthFilter() throws Exception {
        // setup
        Set<Strength> values = Collections.singleton(Strength.TWO);
        Set<String> expected = Collections.singleton("" + Strength.TWO.ordinal());
        // execute
        fixture.saveStrengthFilter(values);
        // validate
        verify(repository).saveStringSet(R.string.filter_strength_key, expected);
    }

    @Test
    public void testGetSecurityFilter() throws Exception {
        // setup
        Security expected = Security.WPA;
        Set<String> values = Collections.singleton("" + expected.ordinal());
        Set<String> defaultValues = EnumUtils.ordinals(Security.class);
        when(repository.getStringSet(R.string.filter_security_key, defaultValues)).thenReturn(values);
        // execute
        Set<Security> actual = fixture.getSecurityFilter();
        // validate
        assertEquals(1, actual.size());
        assertTrue(actual.contains(expected));
        verify(repository).getStringSet(R.string.filter_security_key, defaultValues);
    }

    @Test
    public void testSaveSecurityFilter() throws Exception {
        // setup
        Set<Security> values = Collections.singleton(Security.WEP);
        Set<String> expected = Collections.singleton("" + Security.WEP.ordinal());
        // execute
        fixture.saveSecurityFilter(values);
        // validate
        verify(repository).saveStringSet(R.string.filter_security_key, expected);
    }

    @Test
    public void testToggleWiFiBand() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.wifi_band_key, WiFiBand.GHZ2.ordinal())).thenReturn(WiFiBand.GHZ5.ordinal());
        // execute
        fixture.toggleWiFiBand();
        // validate
        verify(repository).getStringAsInteger(R.string.wifi_band_key, WiFiBand.GHZ2.ordinal());
        verify(repository).save(R.string.wifi_band_key, WiFiBand.GHZ5.toggle().ordinal());
    }

    @Test
    public void testGetCountryCode() throws Exception {
        // setup
        when(context.getResources()).thenReturn(resources);
        when(resources.getConfiguration()).thenReturn(configuration);
        withConfigurationLocale(Locale.UK);
        String defaultValue = Locale.UK.getCountry();
        String expected = Locale.US.getCountry();

        when(repository.getString(R.string.country_code_key, defaultValue)).thenReturn(expected);
        // execute
        String actual = fixture.getCountryCode();
        // validate
        assertEquals(expected, actual);

        verify(repository).getString(R.string.country_code_key, defaultValue);
        verify(context).getResources();
        verify(resources).getConfiguration();
        verifyConfigurationLocale();
    }

    @SuppressWarnings("deprecation")
    private void withConfigurationLocale(Locale locale) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            when(configuration.getLocales()).thenReturn(new LocaleList(locale));
        } else {
            configuration.locale = locale;
        }
    }

    private void verifyConfigurationLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            verify(configuration).getLocales();
        }
    }

    @Test
    public void testGetStartMenu() throws Exception {
        // setup
        when(repository.getStringAsInteger(R.string.start_menu_key, NavigationMenu.ACCESS_POINTS.ordinal())).thenReturn(NavigationMenu.CHANNEL_GRAPH.ordinal());
        // execute
        NavigationMenu actual = fixture.getStartMenu();
        // validate
        assertEquals(NavigationMenu.CHANNEL_GRAPH, actual);
        verify(repository).getStringAsInteger(R.string.start_menu_key, NavigationMenu.ACCESS_POINTS.ordinal());
    }

}