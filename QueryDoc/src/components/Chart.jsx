import React from "react";
import {
  Bar,
  BarChart,
  CartesianGrid,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
} from "recharts";
import { ChartContainer } from "@/components/ui/chart";
import { data } from "./data";
import { FaArrowLeft } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const chartConfig = {
  mongo: {
    label: "MongoDB",
    color: "#f7f7f7",
  },
  postgres: {
    label: "PostgreSQL",
    color: "#2196F3",
  },
};

const CustomTooltip = ({ active, payload }) => {
  const navigate = useNavigate();
  if (active && payload && payload.length) {
    return (
      <div className="bg-black text-white p-2 rounded-md shadow-lg">
        <p className="font-semibold">{`Records: ${payload[0].payload.no_of_records}`}</p>
        <p className="">{`${chartConfig.mongo.label}: ${payload[0].value} ms`}</p>
        <p className="">{`${chartConfig.postgres.label}: ${payload[1].value} ms`}</p>
      </div>
    );
  }
  return null;
};

export default function Chart({ operationType }) {
  const transformData = (data, operationType) => {
    const groupedData = data
      .filter((item) => item.operation_type === operationType)
      .reduce((acc, item) => {
        const qid = item.qid;
        if (!acc[qid]) {
          acc[qid] = {
            no_of_records: item.no_of_records,
            qid: qid,
            mongo: 0,
            postgres: 0,
          };
        }

        if (item.db === "mongo") {
          acc[qid].mongo = item.time;
        } else if (item.db === "postgres") {
          acc[qid].postgres = item.time;
        }
        return acc;
      }, {});

    return Object.values(groupedData);
  };

  const chartData = transformData(data, operationType);

  return (
    <>
      
      <div className="flex justify-center h-[80vh] w-[75vw]">
        <ChartContainer config={chartConfig} className="h-full w-full">
          <BarChart data={chartData} width={500} height={400}>
            <CartesianGrid vertical={false} />
            <XAxis
              dataKey="no_of_records"
              tickLine={false}
              tickMargin={10}
              axisLine={false}
            />
            <YAxis />
            <Tooltip content={<CustomTooltip />} />
            <Legend />
            <Bar
              dataKey="mongo"
              name="MongoDB Time"
              fill={chartConfig.mongo.color}
              radius={4}
            />
            <Bar
              dataKey="postgres"
              name="PostgreSQL Time"
              fill={chartConfig.postgres.color}
              radius={4}
            />
          </BarChart>
        </ChartContainer>
      </div>
    </>
  );
}
