package main

import (
	"sort"
)

// Ordonnanceur selon la durée de calcul restante (shortest remaining time)
func srt(newTasks []task) (currentTask *task) {
	for i := range newTasks {
		fcfsQueue = append(fcfsQueue, &(newTasks[i]))
	}
	sort.Slice(fcfsQueue, func(i, j int) bool {
		return fcfsQueue[i].Duration > fcfsQueue[j].Duration
	})
	for i := range fcfsQueue {
		if fcfsQueue[i].Duration <= 0 {
			fcfsQueue = fcfsQueue[:i]
		}
	}
	// fmt.Println("---------")
	// for i := range fcfsQueue {
	// 	fmt.Println(fcfsQueue[i])
	// }

	if len(fcfsQueue) > 0 {
		fcfsCurrentTask = fcfsQueue[0]
	} else {
		fcfsCurrentTask = nil
	}

	return fcfsCurrentTask
}
