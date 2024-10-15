import React from "react";
import { Bar, BarChart, CartesianGrid, XAxis, YAxis, Tooltip, Legend } from "recharts";
import { ChartContainer } from "@/components/ui/chart"; 
import { data } from "./data";

const chartConfig = {
    mongo: {
        label: "MongoDB",
        color: "#4CAF50",
    },
    postgres: {
        label: "PostgreSQL",
        color: "#2196F3",
    },
};

export default function Chart({ operationType }) {
    // Function to transform data based on the operation type
    const transformData = (data, operationType) => {
        return data
            .filter(item => item.operation_type === operationType)
            .map(item => ({
                no_of_records: item.no_of_records,
                mongo: item.db === "mongo" ? item.time : 0,
                postgres: item.db === "postgres" ? item.time : 0,
            }));
    };

    const chartData = transformData(data, operationType);

    return (
        <div className="h-[80vh] w-[90vw]">
            <ChartContainer config={chartConfig} className="h-full w-full">
                <BarChart data={chartData} width={900} height={400}>
                    <CartesianGrid vertical={false} />
                    <XAxis dataKey="no_of_records" tickLine={false} tickMargin={10} axisLine={false} />
                    <YAxis  />
                    <Tooltip />
                    <Legend />
                    <Bar dataKey="mongo" name="MongoDB Time" fill={chartConfig.mongo.color} radius={4} />
                    <Bar dataKey="postgres" name="PostgreSQL Time" fill={chartConfig.postgres.color} radius={4} />
                </BarChart>
            </ChartContainer>
        </div>
    );
}
