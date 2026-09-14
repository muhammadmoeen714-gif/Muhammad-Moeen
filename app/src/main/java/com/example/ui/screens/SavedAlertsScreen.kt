package com.example.ui.screens

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.VideoLecture
import com.example.ui.components.AlertCard
import com.example.ui.components.LectureCard
import com.example.ui.components.NotificationHelper
import com.example.ui.viewmodel.EntryPrepViewModel

@Composable
fun SavedAlertsScreen(
    viewModel: EntryPrepViewModel,
    onLectureSelected: (VideoLecture) -> Unit,
    onNavigateToAlerts: () -> Unit,
    onNavigateToLectures: () -> Unit,
    modifier: Modifier = Modifier
) {
    val subscribedAlerts by viewModel.subscribedAlerts.collectAsState()
    val bookmarkedIds by viewModel.bookmarkedIds.collectAsState()
    val allLectures = viewModel.allLectures
    val context = LocalContext.current.applicationContext as Application

    val savedLectures = allLectures.filter { bookmarkedIds.contains(it.id) }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabTitles = listOf("Active Alerts (${subscribedAlerts.size})", "Saved Lectures (${savedLectures.size})")

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("saved_alerts_screen")
    ) {
        // Top Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Column {
                Text(
                    text = "My Study Hub & Active Alerts",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Automated deadline tracking and saved lectures",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // Tabs
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.primary
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            fontSize = 13.sp,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    modifier = Modifier.testTag("saved_tab_$index")
                )
            }
        }

        // Tab Content
        Box(modifier = Modifier.fillMaxSize()) {
            if (selectedTabIndex == 0) {
                // Active Alerts Tab
                if (subscribedAlerts.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.NotificationsNone,
                                contentDescription = null,
                                modifier = Modifier.size(56.dp),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No active test alerts yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Go to 'Test Dates' and toggle the auto alert switch on tests you plan to take (e.g. MDCAT, NUST NET, UET ECAT).",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(onClick = { onNavigateToAlerts() }) {
                                Text("Browse Admission Tests")
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        item {
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.NotificationsActive,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(20.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Automatic alert notifications active",
                                            style = MaterialTheme.typography.labelMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }

                                    OutlinedButton(
                                        onClick = {
                                            // Test dispatching a notification
                                            val first = subscribedAlerts.first()
                                            NotificationHelper.sendTestAlertNotification(
                                                context = context,
                                                notificationId = 9999,
                                                universityName = first.shortName,
                                                testName = first.testName,
                                                deadlineOrDate = "Deadline: ${first.registrationDeadline}",
                                                message = "Admission test reminder test"
                                            )
                                        }
                                    ) {
                                        Text("Test Alert", fontSize = 11.sp)
                                    }
                                }
                            }
                        }

                        items(subscribedAlerts, key = { it.id }) { alert ->
                            AlertCard(
                                alert = alert,
                                onToggleAlert = { viewModel.toggleAlertSubscription(it, context) },
                                onUpdateReminderDays = { id, days -> viewModel.updateReminderDays(id, days) },
                                onUpdateNotes = { id, notes -> viewModel.updateAlertNotes(id, notes) }
                            )
                        }
                    }
                }
            } else {
                // Saved Lectures Tab
                if (savedLectures.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.PlayCircleOutline,
                                contentDescription = null,
                                modifier = Modifier.size(56.dp),
                                tint = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "No saved lectures yet",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Tap the bookmark icon on any teacher video lecture to save it here for rapid revision.",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Button(onClick = { onNavigateToLectures() }) {
                                Text("Explore Video Lectures")
                            }
                        }
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(savedLectures, key = { it.id }) { lecture ->
                            LectureCard(
                                lecture = lecture,
                                isBookmarked = true,
                                onBookmarkToggle = { id, isBookmarked ->
                                    viewModel.toggleLectureBookmark(id, isBookmarked)
                                },
                                onClick = {
                                    viewModel.selectLectureForPlayer(lecture)
                                    onLectureSelected(lecture)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
