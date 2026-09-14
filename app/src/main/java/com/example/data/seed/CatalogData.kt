package com.example.data.seed

import com.example.data.local.AdmissionAlertEntity
import com.example.data.model.LectureChapter
import com.example.data.model.PracticeQuestion
import com.example.data.model.Teacher
import com.example.data.model.VideoLecture

object CatalogData {

    val INITIAL_ADMISSION_ALERTS = listOf(
        AdmissionAlertEntity(
            id = "alert_mdcat_2026",
            universityName = "PMDC & Provincial Universities (UHS, NUMS, SZABMU, DUHS, KMU)",
            shortName = "MDCAT 2026",
            testName = "National MDCAT (Medical & Dental Admission Test)",
            category = "Medical",
            registrationDeadline = "2026-07-20",
            testDate = "2026-08-23",
            meritListDate = "2026-09-15",
            city = "Nationwide (All Major Cities & Overseas)",
            feeInPkr = 8000,
            eligibility = "Minimum 60% in HSSC / FSc Pre-Medical (or A-Levels equivalent)",
            aggregateFormulaDescription = "50% MDCAT Score + 40% FSc Pre-Med + 10% Matriculation",
            officialUrl = "https://pmdc.pk",
            isAlertEnabled = true,
            reminderDaysBefore = 3,
            userNotes = "Prepare Biology high-yield notes & Chemistry organic formulas"
        ),
        AdmissionAlertEntity(
            id = "alert_nust_net_4",
            universityName = "National University of Sciences & Technology (NUST)",
            shortName = "NUST NET Series 4",
            testName = "NET-4 (Computer-Based / Paper-Based)",
            category = "Computing",
            registrationDeadline = "2026-06-18",
            testDate = "2026-06-28",
            meritListDate = "2026-07-25",
            city = "Islamabad, Rawalpindi, Karachi, Quetta",
            feeInPkr = 5500,
            eligibility = "Minimum 60% marks in FSc Pre-Engineering / ICS / Pre-Medical (with Math)",
            aggregateFormulaDescription = "75% NET Score + 15% FSc / HSSC + 10% Matric / SSC",
            officialUrl = "https://nust.edu.pk",
            isAlertEnabled = true,
            reminderDaysBefore = 3,
            userNotes = "Final NET series! Target 155+ for Software Engineering"
        ),
        AdmissionAlertEntity(
            id = "alert_uet_ecat",
            universityName = "University of Engineering & Technology (UET) Lahore",
            shortName = "UET ECAT",
            testName = "Combined Engineering Colleges Admission Test (ECAT)",
            category = "Engineering",
            registrationDeadline = "2026-05-30",
            testDate = "2026-06-12",
            meritListDate = "2026-07-10",
            city = "Lahore, Taxila, Multan, Faisalabad, Gujranwala",
            feeInPkr = 3500,
            eligibility = "FSc Pre-Engineering or ICS with Physics & Mathematics (Min 60%)",
            aggregateFormulaDescription = "33% ECAT + 50% Intermediate (FSc) + 17% Matriculation",
            officialUrl = "https://admission.uet.edu.pk",
            isAlertEnabled = true,
            reminderDaysBefore = 5,
            userNotes = "Calculator allowed in ECAT; practice time management"
        ),
        AdmissionAlertEntity(
            id = "alert_fast_nu_test",
            universityName = "FAST National University of Computer & Emerging Sciences (NUCES)",
            shortName = "FAST-NU Test",
            testName = "FAST University Admission Test (CS / SE / AI / DS / EE)",
            category = "Computing",
            registrationDeadline = "2026-07-05",
            testDate = "2026-07-16",
            meritListDate = "2026-07-28",
            city = "Islamabad, Lahore, Karachi, Peshawar, Chiniot-Faisalabad",
            feeInPkr = 3000,
            eligibility = "Min 60% in FSc Pre-Eng or ICS with Mathematics",
            aggregateFormulaDescription = "50% Entry Test (Advanced Math + Basic Math + English) + 50% FSc",
            officialUrl = "https://nu.edu.pk/admissions",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Advanced Math has 50% weightage. Strict negative marking applies"
        ),
        AdmissionAlertEntity(
            id = "alert_giki_test",
            universityName = "Ghulam Ishaq Khan Institute of Eng. Sciences & Tech (GIKI)",
            shortName = "GIKI Test",
            testName = "GIKI Undergraduate Admission Test",
            category = "Engineering",
            registrationDeadline = "2026-06-22",
            testDate = "2026-07-04",
            meritListDate = "2026-07-18",
            city = "Topi (KPK), Rawalpindi, Lahore, Karachi, Peshawar, Multan",
            feeInPkr = 7000,
            eligibility = "FSc Pre-Engineering or ICS (Min 60% in Physics & Math)",
            aggregateFormulaDescription = "85% GIKI Admission Test + 15% FSc Part-1 / O-Levels",
            officialUrl = "https://giki.edu.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Focus heavily on Physics conceptual questions & Advanced Calculus"
        ),
        AdmissionAlertEntity(
            id = "alert_pieas_test",
            universityName = "Pakistan Institute of Engineering and Applied Sciences (PIEAS)",
            shortName = "PIEAS Written Test",
            testName = "PIEAS National Admission Test",
            category = "Engineering",
            registrationDeadline = "2026-06-10",
            testDate = "2026-06-25",
            meritListDate = "2026-07-15",
            city = "Islamabad, Lahore, Karachi, Peshawar, Multan, Faisalabad",
            feeInPkr = 4500,
            eligibility = "FSc Pre-Engineering with min 60% overall and in Physics & Math",
            aggregateFormulaDescription = "60% PIEAS Test + 25% FSc (Part 1) + 15% Matric",
            officialUrl = "https://pieas.edu.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Top ranked engineering institute in Pakistan, strong nuclear/mechanical faculty"
        ),
        AdmissionAlertEntity(
            id = "alert_comsats_nts_nat",
            universityName = "COMSATS University Islamabad (CUI)",
            shortName = "COMSATS (NTS NAT)",
            testName = "NTS National Aptitude Test (NAT-IE / NAT-ICS / NAT-IM)",
            category = "Computing",
            registrationDeadline = "2026-07-12",
            testDate = "2026-07-21",
            meritListDate = "2026-08-05",
            city = "Islamabad, Lahore, Abbottabad, Wah Cantt, Attock, Sahiwal, Vehari",
            feeInPkr = 1200,
            eligibility = "Intermediate (FSc / ICS / I.Com / F.A) with at least 50% marks",
            aggregateFormulaDescription = "50% NTS NAT + 40% Intermediate + 10% Matriculation",
            officialUrl = "https://admissions.comsats.edu.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 2,
            userNotes = "NAT-ICS has Analytical, Quantitative, Verbal and Subject sections"
        ),
        AdmissionAlertEntity(
            id = "alert_hec_lat",
            universityName = "Higher Education Commission (HEC)",
            shortName = "HEC LAT (Law)",
            testName = "Law Admission Test (LAT) for 5-Year LLB",
            category = "Law",
            registrationDeadline = "2026-07-14",
            testDate = "2026-07-26",
            meritListDate = "2026-08-12",
            city = "Major divisional headquarters across Pakistan",
            feeInPkr = 2000,
            eligibility = "Passed Higher Secondary School Certificate (HSSC) or Equivalent",
            aggregateFormulaDescription = "Qualifying cutoff 50/100 marks + University Merit Criteria",
            officialUrl = "https://etc.hec.gov.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Includes Essay (Urdu or English) 15 marks, Personal Statement 10 marks, MCQs 75 marks"
        ),
        AdmissionAlertEntity(
            id = "alert_hec_usat",
            universityName = "Higher Education Commission ETC",
            shortName = "HEC USAT",
            testName = "Undergraduate Studies Admission Test (USAT-E / M / CS / A)",
            category = "General",
            registrationDeadline = "2026-06-30",
            testDate = "2026-07-19",
            meritListDate = "2026-08-01",
            city = "All designated test centers in Punjab, Sindh, KPK, Balochistan, GB, AJK",
            feeInPkr = 2500,
            eligibility = "Students who completed 12 years of education or awaiting HSSC Part-II result",
            aggregateFormulaDescription = "Accepted by 40+ Public Sector Universities across Pakistan",
            officialUrl = "https://etc.hec.gov.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "USAT score is valid for 1 full academic year"
        ),
        AdmissionAlertEntity(
            id = "alert_iba_karachi",
            universityName = "Institute of Business Administration (IBA) Karachi",
            shortName = "IBA Karachi Round 2",
            testName = "IBA Aptitude Test (BBA / BS CS / BS Economics / Math)",
            category = "Business",
            registrationDeadline = "2026-06-24",
            testDate = "2026-07-06",
            meritListDate = "2026-07-22",
            city = "Karachi, Lahore, Islamabad, Quetta, Peshawar",
            feeInPkr = 6000,
            eligibility = "HSSC (Pre-Eng / General Science / Commerce / Arts) with minimum 65%",
            aggregateFormulaDescription = "100% Aptitude Test score for direct admission (Interview if borderline)",
            officialUrl = "https://iba.edu.pk/admissions",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Strict cutoff scores on both Math and English sections independently"
        ),
        AdmissionAlertEntity(
            id = "alert_ned_uet",
            universityName = "NED University of Engineering & Technology",
            shortName = "NED Entry Test",
            testName = "NED Pre-Admission Entry Test Round 2",
            category = "Engineering",
            registrationDeadline = "2026-07-08",
            testDate = "2026-07-23",
            meritListDate = "2026-08-14",
            city = "Main Campus, Karachi",
            feeInPkr = 4000,
            eligibility = "HSSC Pre-Engineering with minimum 60% marks",
            aggregateFormulaDescription = "50% NED Test + 50% Intermediate Board Marks",
            officialUrl = "https://neduet.edu.pk",
            isAlertEnabled = false,
            reminderDaysBefore = 3,
            userNotes = "Top engineering institution in Sindh; 100 MCQs in 2 hours"
        )
    )

    val TEACHERS = listOf(
        Teacher(
            id = "t_waqas",
            name = "Sir Waqas Ali",
            subject = "Physics",
            experience = "14 Years Teaching MDCAT & ECAT",
            institution = "Senior Faculty at Star & KIPS Academy",
            rating = 4.9f,
            totalStudents = "42,000+ Students",
            bio = "Gold medalist physicist known for 30-second shortcut formulas for Vectors, Projectiles, and Electromagnetism in Pakistani entry tests."
        ),
        Teacher(
            id = "t_farhan",
            name = "Prof. Farhan Qureshi",
            subject = "Chemistry",
            experience = "12 Years Teaching Entry Tests",
            institution = "Head of Chemistry at Punjab Group / Stars",
            rating = 4.8f,
            totalStudents = "36,000+ Students",
            bio = "Master of Organic Chemistry reaction roadmaps, Le Chatelier's equilibrium equilibrium tricks, and thermochemistry past papers."
        ),
        Teacher(
            id = "t_bilal",
            name = "Sir Bilal Asghar",
            subject = "Mathematics",
            experience = "11 Years NUST, FAST & ECAT Specialist",
            institution = "Founder of FastMath Pakistan",
            rating = 4.9f,
            totalStudents = "50,000+ Students",
            bio = "Specialist in speed mental math, Calculus without lengthy integration, and Conic Sections geometry shortcuts for NET and FAST entry tests."
        ),
        Teacher(
            id = "t_ayesha",
            name = "Dr. Ayesha Tariq (MBBS)",
            subject = "Biology",
            experience = "9 Years MDCAT Coaching",
            institution = "KEMU Graduate & STEP MDCAT Lead",
            rating = 4.95f,
            totalStudents = "65,000+ Students",
            bio = "Top PMDC syllabus expert who deconstructs confusing human physiology, genetics pedigrees, and tricky PTB / Federal textbook lines."
        ),
        Teacher(
            id = "t_hamza",
            name = "Sir Hamza Rafique",
            subject = "Analytical & Intelligence",
            experience = "8 Years NUST NET & NTS NAT Guru",
            institution = "Entry Test Reasoning Mentor",
            rating = 4.85f,
            totalStudents = "28,000+ Students",
            bio = "Expert in verbal analogies, coding-decoding, logical syllogisms, and spatial pattern completion for NET Series and NTS NAT."
        ),
        Teacher(
            id = "t_zainab",
            name = "Miss Zainab Hassan",
            subject = "English",
            experience = "10 Years Entry Test Verbal Prep",
            institution = "LUMS Alumna & Grammar Specialist",
            rating = 4.75f,
            totalStudents = "31,000+ Students",
            bio = "Decodes Subject-Verb agreement traps, modifier errors, preposition collocations, and the top 200 high-frequency entry test vocabulary list."
        )
    )

    val VIDEO_LECTURES = listOf(
        VideoLecture(
            id = "lec_phys_vectors",
            title = "Vectors & Equilibrium: 30-Second Resultant & Dot/Cross Tricks",
            subject = "Physics",
            testFocus = "ECAT / NET / MDCAT",
            teacherName = "Sir Waqas Ali",
            teacherTitle = "Senior Entry Test Specialist (14 yrs exp)",
            duration = "44 mins",
            durationSeconds = 2640,
            views = "84.2K",
            rating = 4.9f,
            thumbnailUrl = "thumb_vectors",
            videoUrl = "https://example.com/videos/phys_vectors.mp4",
            highYieldSummary = "Master vector resolution into rectangular components, Lami's theorem for 3-force equilibrium, and the angle rule for maximum torque and work.",
            formulasAndTricks = listOf(
                "|A + B| = √(A² + B² + 2AB cosθ). If θ = 90°, |A+B| = √(A²+B²).",
                "If |A + B| = |A - B|, then θ = 90° (Vectors are orthogonal).",
                "Lami's Theorem: F1 / sinα = F2 / sinβ = F3 / sinγ for coplanar forces in equilibrium.",
                "Short trick: Vector cross product magnitude equals Area of parallelogram formed by A and B."
            ),
            chapters = listOf(
                LectureChapter(0, "Introduction & Common Pitfalls"),
                LectureChapter(340, "Unit Vectors & Direction Cosines"),
                LectureChapter(920, "Dot & Cross Product Traps"),
                LectureChapter(1540, "Equilibrium Conditions & Lami's Theorem"),
                LectureChapter(2100, "Solved NUST NET & ECAT Past MCQs")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_vec_1",
                    question = "If the magnitude of the sum of two vectors equals the magnitude of their difference, the angle between them is:",
                    options = listOf("0°", "45°", "90°", "180°"),
                    correctIndex = 2,
                    explanation = "|A + B|² = A² + B² + 2AB cosθ. |A - B|² = A² + B² - 2AB cosθ. Equating both yields 4AB cosθ = 0, so cosθ = 0 => θ = 90°."
                ),
                PracticeQuestion(
                    id = "q_vec_2",
                    question = "Two forces of 6 N and 8 N act at right angles on a particle. The magnitude of the resultant is:",
                    options = listOf("14 N", "10 N", "2 N", "48 N"),
                    correctIndex = 1,
                    explanation = "Resultant = √(6² + 8²) = √(36 + 64) = √100 = 10 N."
                ),
                PracticeQuestion(
                    id = "q_vec_3",
                    question = "A body is in complete equilibrium if:",
                    options = listOf("Only ΣF = 0", "Only Στ = 0", "Both ΣF = 0 and Στ = 0", "Linear acceleration is constant"),
                    correctIndex = 2,
                    explanation = "Complete mechanical equilibrium requires both translational equilibrium (ΣF = 0) and rotational equilibrium (Στ = 0)."
                )
            )
        ),
        VideoLecture(
            id = "lec_bio_physiology",
            title = "Human Nervous Coordination, Action Potential & Synapse (MDCAT High Yield)",
            subject = "Biology",
            testFocus = "MDCAT",
            teacherName = "Dr. Ayesha Tariq (MBBS)",
            teacherTitle = "KEMU Alumna & Senior MDCAT Coach",
            duration = "52 mins",
            durationSeconds = 3120,
            views = "112K",
            rating = 4.95f,
            thumbnailUrl = "thumb_bio_nervous",
            videoUrl = "https://example.com/videos/bio_nervous.mp4",
            highYieldSummary = "Detailed breakdown of Resting Membrane Potential (-70mV), Sodium-Potassium Pump (3 Na+ out, 2 K+ in), Depolarization to +30mV, Refractory periods, and Neurotransmitter transmission across synaptic cleft.",
            formulasAndTricks = listOf(
                "RMP is -70 mV; maintained by Na+/K+ ATPase exporting 3 Na+ for every 2 K+ imported.",
                "Threshold stimulus is around -50 mV to -55 mV to trigger voltage-gated Na+ channels.",
                "Repolarization is driven by efflux of K+ ions through voltage-gated K+ channels.",
                "Saltatory conduction in myelinated axons increases impulse speed up to 120 m/s at Nodes of Ranvier."
            ),
            chapters = listOf(
                LectureChapter(0, "MDCAT Biology Syllabus Overview"),
                LectureChapter(410, "Neuron Structure & Glial Cells"),
                LectureChapter(1120, "Resting Membrane Potential (-70mV) Mechanics"),
                LectureChapter(1850, "Action Potential Wave & Refractory Period"),
                LectureChapter(2480, "Chemical Synapse & Calcium Influx"),
                LectureChapter(2900, "MDCAT Past Paper Tricky Questions")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_bio_1",
                    question = "During the resting potential of a neuron, the inside of the cell is negatively charged primarily due to:",
                    options = listOf("Excess Cl- inside", "Trapped organic anions (proteins) and high permeability to K+ efflux", "Active pumping of positive ions into the cell", "Absence of sodium channels"),
                    correctIndex = 1,
                    explanation = "The intracellular environment contains non-diffusible negative proteins, and K+ leaks out faster than Na+ enters, maintaining negative polarity."
                ),
                PracticeQuestion(
                    id = "q_bio_2",
                    question = "The sodium-potassium exchange pump transports:",
                    options = listOf("2 Na+ out for 3 K+ in", "3 Na+ out for 2 K+ in", "3 Na+ in for 2 K+ out", "Equal numbers of Na+ and K+"),
                    correctIndex = 1,
                    explanation = "The electrogenic Na+/K+ pump transports 3 Na+ ions out of the cytoplasm and brings 2 K+ ions in, consuming 1 ATP molecule."
                )
            )
        ),
        VideoLecture(
            id = "lec_math_calculus",
            title = "Calculus & Limits: L'Hôpital's Rule & Differentiation Shortcuts for FAST/NET",
            subject = "Mathematics",
            testFocus = "FAST / GIKI / NET / ECAT",
            teacherName = "Sir Bilal Asghar",
            teacherTitle = "FAST & NUST Test Specialist (11 yrs exp)",
            duration = "48 mins",
            durationSeconds = 2880,
            views = "96.5K",
            rating = 4.9f,
            thumbnailUrl = "thumb_calculus",
            videoUrl = "https://example.com/videos/math_calculus.mp4",
            highYieldSummary = "Speed techniques to solve 0/0 and ∞/∞ limit forms without algebraic factoring, product and chain rule shortcuts, and finding maxima/minima tangent slopes in 15 seconds.",
            formulasAndTricks = listOf(
                "L'Hôpital's Rule: lim(x->a) [f(x)/g(x)] = lim(x->a) [f'(x)/g'(x)] when 0/0 or ∞/∞ occurs.",
                "lim(x->0) [sin(ax) / sin(bx)] = a/b.",
                "lim(x->0) [(1 - cos(ax)) / x²] = a² / 2.",
                "For y = u/v: derivative numerator is (v u' - u v') / v²."
            ),
            chapters = listOf(
                LectureChapter(0, "The 2-Minute Limit Formula Table"),
                LectureChapter(360, "Indeterminate Forms & L'Hôpital's Master Trick"),
                LectureChapter(1200, "Exponential & Trigonometric Limits"),
                LectureChapter(1920, "Implicit Differentiation Shortcuts"),
                LectureChapter(2450, "FAST Entry Test Past Questions Solved")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_math_1",
                    question = "Evaluate: lim(x->0) [(1 - cos 4x) / x²]",
                    options = listOf("2", "4", "8", "16"),
                    correctIndex = 2,
                    explanation = "Using the shortcut formula lim(x->0) (1 - cos kx)/x² = k²/2 => 4²/2 = 16/2 = 8."
                ),
                PracticeQuestion(
                    id = "q_math_2",
                    question = "If f(x) = x³ - 3x + 2, the local minimum occurs at:",
                    options = listOf("x = -1", "x = 1", "x = 0", "x = 3"),
                    correctIndex = 1,
                    explanation = "f'(x) = 3x² - 3 = 0 => x = ±1. f''(x) = 6x. At x = 1, f''(1) = 6 > 0 (Local Minimum)."
                )
            )
        ),
        VideoLecture(
            id = "lec_chem_organic",
            title = "Organic Chemistry Roadmaps: Reaction Mechanisms & Functional Group Conversion",
            subject = "Chemistry",
            testFocus = "MDCAT / ECAT / PIEAS",
            teacherName = "Prof. Farhan Qureshi",
            teacherTitle = "Head of Chemistry (Stars/PGC)",
            duration = "56 mins",
            durationSeconds = 3360,
            views = "78.4K",
            rating = 4.85f,
            thumbnailUrl = "thumb_organic_chem",
            videoUrl = "https://example.com/videos/chem_organic.mp4",
            highYieldSummary = "Comprehensive reaction matrix connecting Alkyl Halides -> Alcohols -> Aldehydes/Ketones -> Carboxylic Acids, SN1 vs SN2 comparison, and identification tests (Lucas, Tollens, Fehling).",
            formulasAndTricks = listOf(
                "Primary alcohol -> [PCC] -> Aldehyde; Primary alcohol -> [K2Cr2O7/H+] -> Carboxylic Acid.",
                "SN1 favors tertiary halides and polar protic solvents (carbocation intermediate).",
                "SN2 favors primary halides and polar aprotic solvents (single step, inversion of configuration).",
                "Lucas Test: 3° alcohol reacts immediately with ZnCl2/HCl (cloudiness), 2° in 5 min, 1° does not react at room temperature."
            ),
            chapters = listOf(
                LectureChapter(0, "Organic Chemistry Weightage in Pakistani Tests"),
                LectureChapter(480, "SN1 vs SN2 Reaction Kinetics & Stereochemistry"),
                LectureChapter(1320, "Lucas, Tollens & Iodoform Test Shortcuts"),
                LectureChapter(2180, "Aldol Condensation vs Cannizzaro Reaction"),
                LectureChapter(2950, "MDCAT 2024 & 2025 Solved MCQs")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_chem_1",
                    question = "Which of the following compounds gives a positive Iodoform test with yellow precipitate?",
                    options = listOf("Methanol", "Ethanol", "Benzaldehyde", "1-Propanol"),
                    correctIndex = 1,
                    explanation = "Ethanol (CH3CH2OH) possesses the CH3-CH(OH)- grouping required to oxidize into acetaldehyde and yield iodoform (CHI3)."
                ),
                PracticeQuestion(
                    id = "q_chem_2",
                    question = "Aldehydes with no alpha-hydrogen atoms undergo which reaction in the presence of concentrated alkali?",
                    options = listOf("Aldol Condensation", "Cannizzaro Reaction", "Kolbe's Reaction", "Reimer-Tiemann Reaction"),
                    correctIndex = 1,
                    explanation = "Cannizzaro reaction is a self oxidation-reduction reaction of aldehydes lacking alpha-hydrogen atoms (e.g. Formaldehyde, Benzaldehyde)."
                )
            )
        ),
        VideoLecture(
            id = "lec_phys_electromagnetism",
            title = "Electromagnetism: Faraday's Law, Lenz's Law & Transformer Calculations",
            subject = "Physics",
            testFocus = "ECAT / NET / GIKI",
            teacherName = "Sir Waqas Ali",
            teacherTitle = "Senior Entry Test Specialist",
            duration = "46 mins",
            durationSeconds = 2760,
            views = "67.1K",
            rating = 4.88f,
            thumbnailUrl = "thumb_electromag",
            videoUrl = "https://example.com/videos/phys_electromag.mp4",
            highYieldSummary = "Magnetic flux formulas, induced EMF ε = -N(ΔΦ/Δt), Motional EMF ε = -vBL sinθ, Right-hand grip rule, Solenoid field B = μ₀nI, and Transformer turns ratio efficiency.",
            formulasAndTricks = listOf(
                "Motional EMF: ε = vBL sinθ (Maximum when velocity is perpendicular to magnetic field).",
                "Transformer formula: Vp / Vs = Np / Ns = Is / Ip (for 100% ideal transformer).",
                "Energy stored in an inductor: U = 1/2 L I².",
                "Force on moving charge: F = q(v × B) = qvB sinθ."
            ),
            chapters = listOf(
                LectureChapter(0, "Magnetic Force on Charges & Currents"),
                LectureChapter(390, "Magnetic Field of Solenoids & Toroids"),
                LectureChapter(1240, "Faraday's Law & Lenz's Law Sign Conventions"),
                LectureChapter(1950, "Mutual & Self Inductance Speed Tricks"),
                LectureChapter(2420, "UET ECAT Past 5 Years MCQs")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_em_1",
                    question = "If the speed of a conductor moving across a uniform magnetic field is doubled, the induced EMF will:",
                    options = listOf("Remain unchanged", "Be halved", "Be doubled", "Be quadrupled"),
                    correctIndex = 2,
                    explanation = "Motional EMF ε = vBL sinθ is directly proportional to speed v. Doubling speed doubles the induced EMF."
                )
            )
        ),
        VideoLecture(
            id = "lec_intel_analytical",
            title = "Analytical Reasoning & Direction Tests: NUST NET & FAST Masterclass",
            subject = "Analytical & Intelligence",
            testFocus = "NET / FAST / NTS NAT",
            teacherName = "Sir Hamza Rafique",
            teacherTitle = "NTS & NET Intelligence Specialist",
            duration = "38 mins",
            durationSeconds = 2280,
            views = "59.3K",
            rating = 4.92f,
            thumbnailUrl = "thumb_analytical",
            videoUrl = "https://example.com/videos/intel_analytical.mp4",
            highYieldSummary = "Grid and table method to solve complex constraint seating arrangements, blood relation genealogical diagrams, direction turns, and coding substitution in seconds.",
            formulasAndTricks = listOf(
                "Use 2D matrix elimination tables for 4-5 variable grouping puzzles.",
                "Direction rule: A person facing North turns Right (East), Left (West); South is reverse.",
                "In circular arrangements, facing center means clockwise is Left and counter-clockwise is Right.",
                "Letter coding: Assign A=1, B=2 ... Z=26 or reverse (Z=1, A=26)."
            ),
            chapters = listOf(
                LectureChapter(0, "Intelligence Section Structure in NET & FAST"),
                LectureChapter(310, "The 2D Matrix Grid Secret Technique"),
                LectureChapter(1100, "Circular & Linear Seating Arrangement Problems"),
                LectureChapter(1750, "Direction Sense & Displacement Questions"),
                LectureChapter(2120, "NUST NET Series Past Questions Walkthrough")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_ana_1",
                    question = "A person walks 5 km North, turns right and walks 12 km. What is the shortest distance from the starting point?",
                    options = listOf("17 km", "13 km", "7 km", "10 km"),
                    correctIndex = 1,
                    explanation = "Forms a right triangle with legs 5 and 12. Shortest distance = √(5² + 12²) = √(25 + 144) = √169 = 13 km."
                )
            )
        ),
        VideoLecture(
            id = "lec_eng_grammar",
            title = "Top 100 Repeated Entry Test Vocabulary & Subject-Verb Agreement Rules",
            subject = "English",
            testFocus = "MDCAT / NET / GIKI / FAST",
            teacherName = "Miss Zainab Hassan",
            teacherTitle = "LUMS Alumna & English Verbal Coach",
            duration = "42 mins",
            durationSeconds = 2520,
            views = "72.8K",
            rating = 4.82f,
            thumbnailUrl = "thumb_english_vocab",
            videoUrl = "https://example.com/videos/eng_vocab.mp4",
            highYieldSummary = "Rules of proximity for either/or and neither/nor, collective nouns, inverted sentences, and root-word techniques (prefixes/suffixes) to decode any difficult vocabulary word.",
            formulasAndTricks = listOf(
                "Neither... nor / Either... or: Verb agrees with the subject closest to it.",
                "Phrases like 'along with', 'as well as', 'in addition to' do NOT change the number of the subject.",
                "'The number of' takes a singular verb; 'A number of' takes a plural verb.",
                "Each, either, neither, someone, everybody are always singular."
            ),
            chapters = listOf(
                LectureChapter(0, "Common Errors in Pakistani Entry Tests"),
                LectureChapter(380, "Subject-Verb Agreement Master Rules"),
                LectureChapter(1220, "Pronoun Antecedent & Modifier Errors"),
                LectureChapter(1890, "Root Words Vocabulary Mnemonic System"),
                LectureChapter(2340, "Solved Past Paper Sentence Corrections")
            ),
            practiceQuestions = listOf(
                PracticeQuestion(
                    id = "q_eng_1",
                    question = "The professor, along with three of his students, _____ attending the conference.",
                    options = listOf("is", "are", "were", "have been"),
                    correctIndex = 0,
                    explanation = "'Along with...' is a parenthetical prepositional phrase. The singular subject 'The professor' requires the singular verb 'is'."
                ),
                PracticeQuestion(
                    id = "q_eng_2",
                    question = "A number of applicants _____ already submitted their entry test forms.",
                    options = listOf("has", "have", "is", "was"),
                    correctIndex = 1,
                    explanation = "'A number of' is plural in meaning and takes a plural verb ('have'). Note that 'The number of' would take a singular verb."
                )
            )
        )
    )
}
