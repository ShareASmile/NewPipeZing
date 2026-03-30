/*
 * SPDX-FileCopyrightText: 2025-2026 NewPipe e.V. <https://newpipe-ev.de>
 * SPDX-License-Identifier: GPL-3.0-or-later
 */

package org.schabi.newpipe.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import org.schabi.newpipe.R
import org.schabi.newpipe.error.ErrorInfo
import org.schabi.newpipe.error.ErrorUtil
import org.schabi.newpipe.error.ErrorUtil.Companion.createNotification
import org.schabi.newpipe.error.UserAction
import org.schabi.newpipe.settings.viewmodel.SettingsViewModel
import org.schabi.newpipe.ui.SwitchPreference
import org.schabi.newpipe.ui.TextPreference
import org.schabi.newpipe.ui.components.common.ScaffoldWithToolbar

private const val DUMMY = "Dummy"

@Composable
fun DebugScreen(
    backStack: NavBackStack<NavKey>,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val settingsLayoutRedesign by viewModel.settingsLayoutRedesign.collectAsState()

    val isLeakCanaryAvailable by viewModel.isLeakCanaryAvailable.collectAsState()
    val allowHeapDumping by viewModel.allowHeapDumping.collectAsState()
    val allowDisposedExceptions by viewModel.allowDisposedExceptions.collectAsState()
    val showOriginalTimeAgo by viewModel.showOriginalTimeAgo.collectAsState()
    val showCrashThePlayer by viewModel.showCrashThePlayer.collectAsState()

    DebugScreenContent(
        settingsLayoutRedesign = settingsLayoutRedesign,
        isLeakCanaryAvailable = isLeakCanaryAvailable,
        allowHeapDumping = allowHeapDumping,
        allowDisposedExceptions = allowDisposedExceptions,
        showOriginalTimeAgo = showOriginalTimeAgo,
        showCrashThePlayer = showCrashThePlayer,
        onBackClick = { backStack.removeLastOrNull() },
        onToggleAllowHeapDumping = viewModel::toggleAllowHeapDumping,
        onShowMemoryLeaksClick = {
            viewModel.getLeakDisplayActivityIntent()?.let {
                context.startActivity(it)
            }
        },
        onToggleAllowDisposedExceptions = viewModel::toggleAllowDisposedExceptions,
        onToggleShowOriginalTimeAgo = viewModel::toggleShowOriginalTimeAgo,
        onToggleShowCrashThePlayer = viewModel::toggleShowCrashThePlayer,
        onCheckNewStreamsClick = viewModel::checkNewStreams,
        onCrashTheAppClick = {
            throw RuntimeException(DUMMY)
        },
        onShowErrorSnackbarClick = {
            ErrorUtil.showUiErrorSnackbar(
                context,
                DUMMY,
                RuntimeException(DUMMY)
            )
        },
        onCreateErrorNotificationClick = {
            createNotification(
                context,
                ErrorInfo(
                    RuntimeException(DUMMY),
                    UserAction.UI_ERROR,
                    DUMMY
                )
            )
        },
        onToggleSettingsLayoutRedesign = viewModel::toggleSettingsLayoutRedesign,
        modifier = modifier
    )
}

@Composable
fun DebugScreenContent(
    settingsLayoutRedesign: Boolean,
    isLeakCanaryAvailable: Boolean,
    allowHeapDumping: Boolean,
    allowDisposedExceptions: Boolean,
    showOriginalTimeAgo: Boolean,
    showCrashThePlayer: Boolean,
    onBackClick: () -> Unit,
    onToggleAllowHeapDumping: (Boolean) -> Unit,
    onShowMemoryLeaksClick: () -> Unit,
    onToggleAllowDisposedExceptions: (Boolean) -> Unit,
    onToggleShowOriginalTimeAgo: (Boolean) -> Unit,
    onToggleShowCrashThePlayer: (Boolean) -> Unit,
    onCheckNewStreamsClick: () -> Unit,
    onCrashTheAppClick: () -> Unit,
    onShowErrorSnackbarClick: () -> Unit,
    onCreateErrorNotificationClick: () -> Unit,
    onToggleSettingsLayoutRedesign: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ScaffoldWithToolbar(
        title = stringResource(id = R.string.settings_category_debug_title),
        onBackClick = onBackClick
    ) { paddingValues ->
        Column(modifier = modifier.padding(paddingValues)) {
            SwitchPreference(
                title = R.string.leakcanary,
                summary = if (isLeakCanaryAvailable) R.string.enable_leak_canary_summary else R.string.leak_canary_not_available,
                isChecked = allowHeapDumping,
                onCheckedChange = onToggleAllowHeapDumping,
                enabled = isLeakCanaryAvailable
            )
            TextPreference(
                title = R.string.show_memory_leaks,
                summary = if (isLeakCanaryAvailable) null else R.string.leak_canary_not_available,
                onClick = onShowMemoryLeaksClick,
                enabled = isLeakCanaryAvailable
            )
            SwitchPreference(
                title = R.string.enable_disposed_exceptions_title,
                summary = R.string.enable_disposed_exceptions_summary,
                isChecked = allowDisposedExceptions,
                onCheckedChange = onToggleAllowDisposedExceptions
            )
            SwitchPreference(
                title = R.string.show_original_time_ago_title,
                summary = R.string.show_original_time_ago_summary,
                isChecked = showOriginalTimeAgo,
                onCheckedChange = onToggleShowOriginalTimeAgo
            )
            SwitchPreference(
                title = R.string.show_crash_the_player_title,
                summary = R.string.show_crash_the_player_summary,
                isChecked = showCrashThePlayer,
                onCheckedChange = onToggleShowCrashThePlayer
            )
            TextPreference(
                title = R.string.check_new_streams,
                onClick = onCheckNewStreamsClick
            )
            TextPreference(
                title = R.string.crash_the_app,
                onClick = onCrashTheAppClick
            )
            TextPreference(
                title = R.string.show_error_snackbar,
                onClick = onShowErrorSnackbarClick
            )
            TextPreference(
                title = R.string.create_error_notification,
                onClick = onCreateErrorNotificationClick
            )
            SwitchPreference(
                title = R.string.settings_layout_redesign,
                isChecked = settingsLayoutRedesign,
                onCheckedChange = onToggleSettingsLayoutRedesign
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DebugScreenPreview() {
    DebugScreenContent(
        settingsLayoutRedesign = false,
        isLeakCanaryAvailable = true,
        allowHeapDumping = false,
        allowDisposedExceptions = true,
        showOriginalTimeAgo = false,
        showCrashThePlayer = true,
        onBackClick = {},
        onToggleAllowHeapDumping = {},
        onShowMemoryLeaksClick = {},
        onToggleAllowDisposedExceptions = {},
        onToggleShowOriginalTimeAgo = {},
        onToggleShowCrashThePlayer = {},
        onCheckNewStreamsClick = {},
        onCrashTheAppClick = {},
        onShowErrorSnackbarClick = {},
        onCreateErrorNotificationClick = {},
        onToggleSettingsLayoutRedesign = {}
    )
}
