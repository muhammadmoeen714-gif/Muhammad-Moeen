package com.example.ui.screens

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.viewmodel.AggregateFormulaPreset
import com.example.ui.viewmodel.EntryPrepViewModel

@Composable
fun AggregateCalculatorScreen(
    viewModel: EntryPrepViewModel,
    modifier: Modifier = Modifier
) {
    val calcState by viewModel.calcState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("aggregate_calculator_screen")
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
                    text = "Pakistani University Aggregate Calculator",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Exact formulas for MDCAT, NUST NET, UET ECAT, FAST, GIKI",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // University Preset Chips
            item {
                Text(
                    text = "Select University / Test Formula:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(AggregateFormulaPreset.values()) { preset ->
                        FilterChip(
                            selected = calcState.selectedPreset == preset,
                            onClick = { viewModel.setCalcPreset(preset) },
                            label = { Text(preset.title) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                }
            }

            // Formula Breakdown Box
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = calcState.selectedPreset.title,
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = calcState.selectedPreset.description,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Score Result Gauge
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f),
                                        Color.Transparent
                                    )
                                )
                            )
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "YOUR CALCULATED AGGREGATE",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = if (calcState.calculatedAggregate != null) "${calcState.calculatedAggregate}%" else "--",
                                fontSize = 42.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.testTag("calculated_aggregate_text")
                            )

                            Text(
                                text = "Formula: ${calcState.selectedPreset.description}",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Marks Input Fields
            item {
                Text(
                    text = "Enter Your Academic Marks",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
            }

            // Entry Test Marks (Primary weight)
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Entry Test Score (Weight: ${(calcState.selectedPreset.testWeight * 100).toInt()}%)",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = calcState.testObtained,
                                onValueChange = { viewModel.updateCalcField(testObt = it) },
                                label = { Text("Obtained Marks") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_test_obtained"),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = calcState.testTotal,
                                onValueChange = { viewModel.updateCalcField(testTot = it) },
                                label = { Text("Total Marks") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_test_total"),
                                singleLine = true
                            )
                        }
                    }
                }
            }

            // Intermediate (FSc / HSSC) Marks
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "FSc / HSSC Marks (Weight: ${(calcState.selectedPreset.fscWeight * 100).toInt()}%)",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            OutlinedTextField(
                                value = calcState.fscObtained,
                                onValueChange = { viewModel.updateCalcField(fscObt = it) },
                                label = { Text("Obtained (Part 1 or 2)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_fsc_obtained"),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = calcState.fscTotal,
                                onValueChange = { viewModel.updateCalcField(fscTot = it) },
                                label = { Text("Total Marks (550 or 1100)") },
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                modifier = Modifier
                                    .weight(1f)
                                    .testTag("input_fsc_total"),
                                singleLine = true
                            )
                        }
                    }
                }
            }

            // Matriculation (SSC) Marks (if applicable)
            if (calcState.selectedPreset.matricWeight > 0) {
                item {
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = "Matric / SSC Marks (Weight: ${(calcState.selectedPreset.matricWeight * 100).toInt()}%)",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                OutlinedTextField(
                                    value = calcState.matricObtained,
                                    onValueChange = { viewModel.updateCalcField(matricObt = it) },
                                    label = { Text("Obtained Marks") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("input_matric_obtained"),
                                    singleLine = true
                                )
                                OutlinedTextField(
                                    value = calcState.matricTotal,
                                    onValueChange = { viewModel.updateCalcField(matricTot = it) },
                                    label = { Text("Total Marks") },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("input_matric_total"),
                                    singleLine = true
                                )
                            }
                        }
                    }
                }
            }

            // Historical Reference Closing Merits
            item {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
                    )
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.School, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Past Year Closing Merit Benchmarks",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))

                        val benchmarks = when (calcState.selectedPreset) {
                            AggregateFormulaPreset.MDCAT -> listOf(
                                "King Edward Medical University (KEMU)" to "93.45%",
                                "Allama Iqbal Medical College (AIMC)" to "92.10%",
                                "Rawalpindi Medical University (RMU)" to "91.20%",
                                "Nishtar Medical University Multan" to "90.85%",
                                "Punjab Public Dental Colleges (BDS)" to "89.90%"
                            )
                            AggregateFormulaPreset.NUST_NET -> listOf(
                                "Software Engineering (SEECS)" to "79.20%",
                                "Computer Science (SEECS)" to "77.80%",
                                "Electrical Engineering (SEECS)" to "68.40%",
                                "Mechanical Engineering (SMME)" to "67.10%",
                                "Civil Engineering (NICE)" to "63.50%"
                            )
                            AggregateFormulaPreset.UET_ECAT -> listOf(
                                "Computer Science (UET Lahore)" to "81.50%",
                                "Mechanical Engineering (Main)" to "74.80%",
                                "Civil Engineering (Main)" to "72.40%",
                                "Chemical Engineering" to "69.10%"
                            )
                            AggregateFormulaPreset.FAST_NU -> listOf(
                                "BS Computer Science (Islamabad)" to "76.50%",
                                "BS Software Engineering (Lahore)" to "74.20%",
                                "BS Artificial Intelligence (Lahore)" to "72.80%",
                                "BS Cyber Security (Karachi)" to "68.00%"
                            )
                            AggregateFormulaPreset.GIKI -> listOf(
                                "Computer Science & Artificial Intelligence" to "82.00%",
                                "Mechanical & Materials Engineering" to "74.50%",
                                "Electrical & Computer Engineering" to "71.00%"
                            )
                            AggregateFormulaPreset.COMSATS_NAT -> listOf(
                                "BS Computer Science (Islamabad)" to "86.50%",
                                "BS Software Engineering (Lahore)" to "84.00%",
                                "BS Data Science" to "80.50%"
                            )
                        }

                        benchmarks.forEach { (program, merit) ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = program,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = merit,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
